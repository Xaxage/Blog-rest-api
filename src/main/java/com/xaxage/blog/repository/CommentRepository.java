package com.xaxage.blog.repository;

import com.xaxage.blog.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findByPostEntityId(Integer postId);

}
