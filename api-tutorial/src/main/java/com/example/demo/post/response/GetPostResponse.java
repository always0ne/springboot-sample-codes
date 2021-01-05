package com.example.demo.post.response;

import com.example.demo.post.model.dto.PostDto;
import lombok.Getter;

@Getter
public class GetPostResponse {
    private String title;
    private String body;
    private Long views;

    public GetPostResponse(PostDto postDto){
        this.title = postDto.getTitle();
        this.body = postDto.getBody();
        this.views = postDto.getViews();
    }
}
