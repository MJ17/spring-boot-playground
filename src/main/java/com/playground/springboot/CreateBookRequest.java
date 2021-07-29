package com.playground.springboot;

import lombok.Data;

@Data
public class CreateBookRequest {
    String title;
    String author;
    String publisher;
}
