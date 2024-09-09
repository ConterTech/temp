package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
    public RoleEntity findByName(String name);
}
