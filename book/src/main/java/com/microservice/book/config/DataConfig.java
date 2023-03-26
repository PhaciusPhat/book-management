package com.microservice.book.config;

import com.microservice.book.entities.Book;
import com.microservice.book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataConfig implements ApplicationRunner {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        List<Book> books = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Book booki = new Book();
            booki.setName("book" + i);
            booki.setPrice(i*1000L);
            booki.setAmount(i+10L);
            booki.setAuthorId((i%3)+1);
            books.add(booki);
        }
        bookRepository.saveAll(books);
    }
}
