package com.example.borrow_service.service;


import com.example.borrow_service.feign.BookInterface;
import com.example.borrow_service.feign.UserInterface;
import com.example.borrow_service.model.Book;
import com.example.borrow_service.model.Borrow;
import com.example.borrow_service.model.BorrowRequest;
import com.example.borrow_service.model.User;
import com.example.borrow_service.repo.ArchiveRepo;
import com.example.borrow_service.repo.BorrowRepo;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class BorrowService {
    @Autowired
    BorrowRepo repo;

    @Autowired
    ArchiveRepo archiveRepo;

    @Autowired
    BookInterface bookInterface;

    @Autowired
    UserInterface userInterface;


    public List<Borrow> getAllBorrows() {
        return repo.findAll();
    }

    public Borrow getById(int id) {
        return repo.findById(id).get();
    }

    @Transactional
    public ResponseEntity<String> borrow(BorrowRequest borrowRequest) {

        if (!borrowRequest.getBorrowDate().isBefore(borrowRequest.getReturnDate())) {
            return ResponseEntity.badRequest().body("error: borrow date must be before return date");
        }

        List<Borrow> borrowsOfBook = repo.findAllByBookId(borrowRequest.getBookId());

        if(!borrowsOfBook.isEmpty()){
            return ResponseEntity.badRequest().body("error: book has already been borrowed");
        }

        User user = null;
        Book book = null;
        User librarian = null;

        try{
            user = userInterface.getById(borrowRequest.getUserId());
        }catch(FeignException e){
            return ResponseEntity.badRequest().body("invalid user id: " + e.getMessage());
        }
        try{
            librarian = userInterface.getById(borrowRequest.getLibrarianId());
        }catch(FeignException e){
            return ResponseEntity.badRequest().body("invalid librarian id: " + e.getMessage());
        }
        try{
            book = bookInterface.getBookById(borrowRequest.getBookId());
        }catch(FeignException e){
            return ResponseEntity.badRequest().body("invalid book id: " + e.getMessage());
        }

        if (book == null) return ResponseEntity.badRequest().body("error: book not found");
        if (user == null || user.getUsername().isEmpty()) return ResponseEntity.badRequest().body("error: user not found");
        if (librarian == null || librarian.getUsername().isEmpty()) return ResponseEntity.badRequest().body("error: user not found");

        if(!Objects.equals(user.getRole(), "CUSTOMER")){
            return ResponseEntity.badRequest().body("only customers can borrow books");
        }
        if(!Objects.equals(librarian.getRole(), "LIBRARIAN")){
            return ResponseEntity.badRequest().body("librarian id does not belong to a librarian");
        }



        Borrow borrow = new Borrow();
        borrow.setUserId(borrowRequest.getUserId());
        borrow.setUserName(user.getUsername());
        borrow.setLibrarianId(librarian.getId());
        borrow.setLibrarianName(librarian.getUsername());
        borrow.setBookId(borrowRequest.getBookId());
        borrow.setBookTitle(book.getName());
        borrow.setBorrowDate(borrowRequest.getBorrowDate());
        borrow.setReturnDate(borrowRequest.getReturnDate());

        repo.save(borrow);

        return ResponseEntity.ok().body("success");
    }

    @Transactional
    public ResponseEntity<String> returnBook(String bookName, LocalDate date, String username) {

        Borrow borrow = repo.findByBookNameAndUserName(bookName, username);

        if(borrow == null){
            return ResponseEntity.badRequest().body("borrow not found (check the spelling of the book name and username)");
        }

        if(date.isAfter(borrow.getReturnDate())){
            archiveRepo.save(borrow);
            repo.delete(borrow);
            long dayDiff = date.toEpochDay() - borrow.getReturnDate().toEpochDay();
            return ResponseEntity.ok().body("YOU ARE LATE BY " + dayDiff + " DAYS!!!");
        }
        if (date.isBefore(borrow.getBorrowDate())){
            return ResponseEntity.badRequest().body("are you a time traveler?");
        }

        archiveRepo.save(borrow);
        repo.delete(borrow);

        return ResponseEntity.ok().body("book returned");



    }
}
