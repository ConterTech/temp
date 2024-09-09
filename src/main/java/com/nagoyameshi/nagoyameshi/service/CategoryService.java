package com.nagoyameshi.nagoyameshi.service;

import org.springframework.stereotype.Service;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;
import com.nagoyameshi.nagoyameshi.form.CategoryRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.CategoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 登録
    @Transactional
    public void addCategory(CategoryRegisterForm categoryRegisterForm){
        CategoryEntity category = new CategoryEntity();

        category.setCategory(categoryRegisterForm.getCategory());

        categoryRepository.save(category);
    }
}
