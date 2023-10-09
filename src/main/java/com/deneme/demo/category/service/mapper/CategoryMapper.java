package com.deneme.demo.category.service.mapper;

import com.deneme.demo.category.domain.Category;
import com.deneme.demo.category.service.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toCategoryDto(List<Category> categories);
    Category toCategory(CategoryDto categoryDto);
}
