package com.microservice.book.services;

import com.microservice.book.entities.Book;
import com.microservice.book.repositories.BookRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private Logger logger;
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        logger.info("Start get books");
        List<Book> books = bookRepository.findAll();
        logger.info("End get books");
        return books;
    }
}
