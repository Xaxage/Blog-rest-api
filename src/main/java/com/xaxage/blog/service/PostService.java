package com.xaxage.blog.service;

import com.xaxage.blog.payload.PostDto;
import com.xaxage.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Integer id);

    PostDto updatePost(PostDto postDto, Integer id);

    void deletePostById(Integer id);
}
