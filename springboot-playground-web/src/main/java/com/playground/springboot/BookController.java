package com.playground.springboot;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id) {
        log.error("get book");
        return "Book id is " + id;
    }

    @PostMapping
    public String createBook(@RequestHeader HttpHeaders headers,
                             @RequestBody CreateBookRequest request) {
        log.error("post book");

        String authorization = headers.get(HttpHeaders.AUTHORIZATION).get(0);

        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());

        return book.toString();
    }

}
