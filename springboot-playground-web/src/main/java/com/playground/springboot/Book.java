package com.playground.springboot;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Book extends AbstractPersistable<String> {
    @Column(unique = true)
    String id;
    String title;
    String author;
    String publisher;

    public Book(String id) {
        this.id = id;
    }
}