package com.deneme.demo.category.service;

import com.deneme.demo.category.domain.Category;
import com.deneme.demo.category.service.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    Category getCategoryById(Long id);
    CategoryDto updateCategory(CategoryDto categoryDto);
    CategoryDto insertCategory(CategoryDto categoryDto);
    void removeCategory(Long id);
    void removeAllCategories();
}
