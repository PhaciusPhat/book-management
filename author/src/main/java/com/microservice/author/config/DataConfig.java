package com.microservice.author.config;

import com.microservice.author.entities.Author;
import com.microservice.author.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataConfig implements ApplicationRunner {
    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Author author = new Author();
            author.setName("author" + i);
        }
        authorRepository.saveAll(authors);
    }
}
