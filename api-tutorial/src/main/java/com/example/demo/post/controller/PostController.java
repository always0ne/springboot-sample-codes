package com.example.demo.post.controller;

import com.example.demo.post.model.dto.PostDto;
import com.example.demo.post.request.UpdatePostRequest;
import com.example.demo.post.response.GetPostResponse;
import com.example.demo.post.response.GetPostsResponse;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetPostsResponse getPosts() {
        List<PostDto> posts = this.postService.getPosts();
        return new GetPostsResponse(posts);
    }


    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public GetPostResponse getPost(
            @PathVariable long postId
    ) {
        PostDto post = this.postService.getPost(postId);
        return new GetPostResponse(post);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GetPostResponse addPost(
            @RequestBody UpdatePostRequest updatePostRequest
    ) {
        PostDto post = this.postService.addPost(updatePostRequest.getTitle(), updatePostRequest.getBody());
        return new GetPostResponse(post);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public GetPostResponse updatePost(
            @PathVariable long postId,
            @RequestBody UpdatePostRequest updatePostRequest
    ) {
        PostDto post = this.postService.updatePost(postId, updatePostRequest.getTitle(), updatePostRequest.getBody());
        return new GetPostResponse(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(
            @PathVariable long postId
    ) {
        this.postService.deletePost(postId);
    }
}
