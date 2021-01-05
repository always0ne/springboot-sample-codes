package com.example.demo.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDto {
    private String title;
    private String body;
    private Long views;
}
