package com.example.demo.post.service;

import com.example.demo.post.exception.exceptions.PostNotFoundException;
import com.example.demo.post.model.dto.PostDto;
import com.example.demo.post.model.entity.Post;
import com.example.demo.post.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
        return this.postRepository.findAll().stream()
                .map(post -> new PostDto(post.getTitle(), post.getBody(), post.getViews()))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostDto getPost(long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.increaseViews();
        return new PostDto(post.getTitle(), post.getBody(), post.getViews());
    }

    @Transactional
    public PostDto addPost(String title, String body) {
        Post post = this.postRepository.save(new Post(title, body));
        return new PostDto(post.getTitle(), post.getBody(), post.getViews());
    }

    @Transactional
    public PostDto updatePost(long postId, String title, String body) {
        Post post = this.postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.updatePost(title, body);
        return new PostDto(post.getTitle(), post.getBody(), post.getViews());
    }

    @Transactional
    public void deletePost(long postId) {
        this.postRepository.deleteById(postId);
    }
}
