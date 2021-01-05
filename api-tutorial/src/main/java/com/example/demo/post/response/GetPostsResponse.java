package com.example.demo.post.response;

import com.example.demo.post.model.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPostsResponse {
    List<PostDto> posts;
}
