package com.playground.springboot;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long teamId;

    @Builder
    public Player(String name, Long teamId) {
        this.name = name;
        this.teamId = teamId;
    }
}
