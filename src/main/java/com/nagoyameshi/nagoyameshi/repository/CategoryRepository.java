package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    public CategoryEntity findByCategory(String category);
    public CategoryEntity findByCategoryId(Integer categoryId);
    public Page<CategoryEntity> findByCategoryLike(String keyword, Pageable Pageable);
}
