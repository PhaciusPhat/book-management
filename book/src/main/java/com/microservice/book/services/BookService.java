package com.microservice.book.services;

import com.microservice.book.entities.Book;
import com.microservice.book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public List<Book> getBooksByAuthor(Integer id){
        List<Book> books = bookRepository.getBooksByAuthorId(id);
        return books;
    }
}
