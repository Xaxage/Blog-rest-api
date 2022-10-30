package com.xaxage.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {

    private Integer id;

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 2, message = "Post title should 2 characters at least")
    private String title;

    @NotEmpty(message = "Description should not be null or empty")
    @Size(min = 10, message = "Post description should be 10 characters at least")
    private String description;

    @NotEmpty(message = "Content should not be null or empty")
    private String content;
    Set<CommentDto> comments = new HashSet<>();

}
