package com.xaxage.blog.utils.mapper;

import com.xaxage.blog.entity.CommentEntity;
import com.xaxage.blog.payload.CommentDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    public CommentDto mapEntityToDto(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(commentEntity.getId());
        commentDto.setName(commentEntity.getName());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setEmail(commentEntity.getEmail());

        return commentDto;
    }

    public CommentEntity mapDtoToEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(commentDto.getId());
        commentEntity.setName(commentDto.getName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setContent(commentDto.getContent());

        return commentEntity;
    }

    public Set<CommentDto> mapEntitiesToDtos(Set<CommentEntity> commentEntities) {
        return commentEntities.stream().map(this::mapEntityToDto).collect(Collectors.toSet());
    }

    public Set<CommentEntity> mapDtosToEntities(Set<CommentDto> commentDtos) {
        return commentDtos.stream().map(this::mapDtoToEntity).collect(Collectors.toSet());
    }

}
