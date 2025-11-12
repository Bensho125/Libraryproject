package com.example.borrow_service.Controller;

import com.example.borrow_service.model.Borrow;
import com.example.borrow_service.model.BorrowRequest;
import com.example.borrow_service.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("borrow")
public class BorrowController {

    @Autowired
    BorrowService service;

    @GetMapping("/getAll")
    public List<Borrow> getAllBorrows(){
        return service.getAllBorrows();
    }

    @GetMapping("getById/{id}")
    public Borrow getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrow(@RequestBody BorrowRequest borrowRequest){
        return service.borrow(borrowRequest);
    }

    @PostMapping("returnBook")
    public ResponseEntity<String> returnBook(@RequestParam String bookName, @RequestParam LocalDate date, @RequestParam String username){
        return service.returnBook(bookName, date, username);
    }
}
