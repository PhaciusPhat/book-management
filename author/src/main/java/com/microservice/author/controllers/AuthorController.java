package com.microservice.author.controllers;

import com.microservice.author.config.BookServiceProxy;
import com.microservice.author.entities.Author;
import com.microservice.author.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookServiceProxy proxy;

    @GetMapping
    public ResponseEntity<?> getAuthors(){
        return ResponseEntity.ok(authorService.getAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Integer id){
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.status(author != null ? 200 : 404).body(author);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable Integer id){
        return ResponseEntity.ok(proxy.getBooksByAuthor(id));
    }
}
