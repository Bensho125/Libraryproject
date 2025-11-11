package com.example.book_service.feign;

import com.example.book_service.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service")
public interface BookInterface {

    @GetMapping("/book/getAllBooks")
    public List<Book> getAllBooks();

    @GetMapping("/book/getBookById/{id}")
    public Book getBookById(@PathVariable int id);

    @PostMapping("/book/addBook")
    public String addBook(@RequestBody Book book);

    @PutMapping("/book/updateBook/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book);

    @DeleteMapping("/book/deleteBook/{id}")
    public String deleteBook(@PathVariable int id);
}
