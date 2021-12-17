package com.playground.springboot;

import lombok.Data;

@Data
public class CreatePlayerRequest {
    private String name;
    private Long teamId;
}
