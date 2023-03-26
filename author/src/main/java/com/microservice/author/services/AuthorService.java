package com.microservice.author.services;

import com.microservice.author.entities.Author;
import com.microservice.author.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id){
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }
}
