package com.microservice.author.config;

import com.microservice.author.entities.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("BookService")
public interface BookServiceProxy {

    @GetMapping("/book/author/{id}")
    List<Book> getBooksByAuthor(@PathVariable("id") Integer id);
}
