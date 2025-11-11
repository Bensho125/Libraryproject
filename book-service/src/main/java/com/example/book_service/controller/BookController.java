package com.example.book_service.controller;


import com.example.book_service.BookServiceApplication;
import com.example.book_service.model.Book;
import com.example.book_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("getAllBooks")
    public List<Book> getAllBooks(){

        return bookService.getAllBooks();
    }

    @GetMapping("getBookById/{id}")
    public Book getBookById(@PathVariable int id){

        return bookService.getBookById(id);
    }

    @PostMapping("addBook")
    public String addBook(@RequestBody Book book){

        return bookService.addBook(book);
    }

    @PutMapping("updateBook/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book){

        return bookService.updateBook(id, book);
    }

    @DeleteMapping("deleteBook/{id}")
    public String deleteBook(@PathVariable int id){

        return bookService.deleteBook(id);
    }

}
