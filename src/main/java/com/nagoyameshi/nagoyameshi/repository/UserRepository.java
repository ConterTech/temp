package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    public UserEntity findByEmail(String email);
    public Optional<UserEntity> findByUserId(Integer userId);
    public Page<UserEntity> findByName(String name, Pageable pageable);
    public Page<UserEntity> findByEmail(String email, Pageable pageable);
}
