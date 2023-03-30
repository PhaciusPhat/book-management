package com.microservice.book.controllers;

import com.microservice.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable("id") Integer id){
        return ResponseEntity.ok(bookService.getBooksByAuthor(id));
    }
}
