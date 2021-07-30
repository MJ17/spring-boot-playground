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
public class Player extends AbstractPersistable<String> {
    @Column(unique = true)
    String id;
    String name;

    public Player(String id) {
        this.id = id;
    }
}
