package com.xaxage.blog.utils.mapper;

import com.xaxage.blog.entity.PostEntity;
import com.xaxage.blog.payload.PostDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    private final CommentMapper commentMapper;

    public PostMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public PostDto mapEntityToDto(PostEntity postEntity) {
        PostDto postDto = new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());
        postDto.setComments(commentMapper.mapEntitiesToDtos(postEntity.getCommentEntities()));

        return postDto;
    }

    public PostEntity mapDtoToEntity(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());
        postEntity.setCommentEntities(commentMapper.mapDtosToEntities(postDto.getComments()));

        return postEntity;
    }

    public List<PostDto> mapEntitiesToDtos(List<PostEntity> postEntities) {
        return postEntities.stream().map(this::mapEntityToDto).toList();
    }

    public List<PostEntity> mapDtosToEntities(List<PostDto> commentDtos) {
        return commentDtos.stream().map(this::mapDtoToEntity).toList();
    }

}
