package com.xaxage.blog.repository;

import com.xaxage.blog.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(String name);

}
