package com.deneme.demo.category.service.impl;

import com.deneme.demo.category.domain.Category;
import com.deneme.demo.category.repository.CategoryRepository;
import com.deneme.demo.category.service.CategoryService;
import com.deneme.demo.category.service.dto.CategoryDto;
import com.deneme.demo.product.domain.Product;
import com.deneme.demo.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(categoryDto.getId()).orElse(null);
        if (existingCategory != null) {
            BeanUtils.copyProperties(categoryDto, existingCategory);
            categoryRepository.save(existingCategory);
            return categoryDto;
        }
        return null;
    }

    @Override
    public CategoryDto insertCategory(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        Category savedCategory = categoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    @Override
    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void removeAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            List<Product> products = productRepository.findByCategory(category);
            productRepository.deleteAll(products);
            categoryRepository.delete(category);
        }
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }
}

