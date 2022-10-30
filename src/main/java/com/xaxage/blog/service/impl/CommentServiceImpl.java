package com.xaxage.blog.service.impl;

import com.xaxage.blog.entity.CommentEntity;
import com.xaxage.blog.entity.PostEntity;
import com.xaxage.blog.exception.BlogAPIException;
import com.xaxage.blog.exception.ResourceNotFoundException;
import com.xaxage.blog.payload.CommentDto;
import com.xaxage.blog.repository.CommentRepository;
import com.xaxage.blog.repository.PostRepository;
import com.xaxage.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Integer postId, CommentDto commentDto) {
        CommentEntity commentEntity = mapToEntity(commentDto);

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        commentEntity.setPostEntity(postEntity);

        return mapToDto(commentRepository.save(commentEntity));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Integer postId) {
        List<CommentEntity> commentEntities = commentRepository.findByPostEntityId(postId);
        return commentEntities.stream().map(this::mapToDto).toList();
    }

    @Override
    public CommentDto getCommentById(Integer postId, Integer commentId) {
        return mapToDto(checkIfPostAndCommentBelongToEachOther(postId, commentId));
    }

    @Override
    public CommentDto updateComment(Integer postId, Integer commentId, CommentDto commentDto) {
        CommentEntity commentEntity = checkIfPostAndCommentBelongToEachOther(postId, commentId);

        commentEntity.setName(commentDto.getName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setContent(commentDto.getContent());

        return mapToDto(commentRepository.save(commentEntity));
    }

    @Override
    public void deleteComment(Integer postId, Integer commentId) {
        commentRepository.delete(checkIfPostAndCommentBelongToEachOther(postId, commentId));
    }

    private CommentEntity checkIfPostAndCommentBelongToEachOther(Integer postId, Integer commentId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

        if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post");
        }

        return commentEntity;
    }

    private CommentDto mapToDto(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(commentEntity.getId());
        commentDto.setName(commentEntity.getName());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setEmail(commentEntity.getEmail());

        return commentDto;
    }

    private CommentEntity mapToEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(commentDto.getId());
        commentEntity.setName(commentDto.getName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setContent(commentDto.getContent());

        return commentEntity;
    }
}
