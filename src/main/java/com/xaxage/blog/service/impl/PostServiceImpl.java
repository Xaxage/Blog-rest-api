package com.xaxage.blog.service.impl;

import com.xaxage.blog.entity.PostEntity;
import com.xaxage.blog.exception.ResourceNotFoundException;
import com.xaxage.blog.payload.PostDto;
import com.xaxage.blog.payload.PostResponse;
import com.xaxage.blog.repository.PostRepository;
import com.xaxage.blog.service.PostService;
import com.xaxage.blog.utils.mapper.PostMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;

    public PostServiceImpl(PostMapper postMapper, PostRepository postRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        PostEntity postEntity = postMapper.mapDtoToEntity(postDto);
        PostEntity savedPostEntity = postRepository.save(postEntity);

        return postMapper.mapEntityToDto(savedPostEntity);
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<PostEntity> pageOfPosts = postRepository.findAll(pageable);

        List<PostEntity> listOfPostEntities = pageOfPosts.getContent();

        List<PostDto> content = postMapper.mapEntitiesToDtos(listOfPostEntities);

        return new PostResponse(
                content,
                pageOfPosts.getNumber(),
                pageOfPosts.getSize(),
                pageOfPosts.getTotalElements(),
                pageOfPosts.getTotalPages(),
                pageOfPosts.isLast());
    }

    @Override
    public PostDto getPostById(Integer id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );

        return postMapper.mapEntityToDto(postEntity);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );

        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());

        PostEntity updatedPostEntity = postRepository.save(postEntity);
        return postMapper.mapEntityToDto(updatedPostEntity);
    }

    @Override
    public void deletePostById(Integer id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );

        postRepository.delete(postEntity);
    }

}
