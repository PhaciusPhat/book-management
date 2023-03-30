package com.microservice.author.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Book {
    private UUID id;
    private String name;
    private Long price;
    private Long amount;
    private Integer authorId;
}
