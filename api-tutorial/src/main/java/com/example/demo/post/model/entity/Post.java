package com.example.demo.post.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(nullable = false)
    private Long views;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
        this.views = (long) 0;
    }

    public void increaseViews() {
        this.views++;
    }

    public void updatePost(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
