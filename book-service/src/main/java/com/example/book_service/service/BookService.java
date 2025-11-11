package com.example.book_service.service;


import com.example.book_service.model.Book;
import com.example.book_service.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(int id) {

        return bookRepo.findById(id).get();
    }

    public String addBook(Book book) {

        try{
            bookRepo.save(book);
            return "success";
        } catch (Exception e){
            return "something went wrong";
        }
    }

    public String updateBook(int id, Book book) {
        if(!bookRepo.existsById(id)){
            return "book does not exist";
        }
        Book oldBook = bookRepo.findById(id).get();
        oldBook.setName(book.getName());
        oldBook.setDescription(book.getDescription());
        oldBook.setGenre(book.getGenre());
        oldBook.setReleaseYear(book.getReleaseYear());

        bookRepo.save(oldBook);

        return "success";

    }

    public String deleteBook(int id) {
        if(!bookRepo.existsById(id)){
            return "book does not exist";
        }

        bookRepo.deleteById(id);

        return "success";
    }
}
