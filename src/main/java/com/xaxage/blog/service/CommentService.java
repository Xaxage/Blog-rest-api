package com.xaxage.blog.service;

import com.xaxage.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Integer postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Integer postId);

    CommentDto getCommentById(Integer postId, Integer commentId);

    CommentDto updateComment(Integer postId, Integer commentId, CommentDto commentDto);

    void deleteComment(Integer postId, Integer commentId);
}
