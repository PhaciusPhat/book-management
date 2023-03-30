package com.microservice.book.repositories;

import com.microservice.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> getBooksByAuthorId(Integer authorId);
}
