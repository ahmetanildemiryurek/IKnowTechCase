package com.deneme.demo.category.web.controller;

import com.deneme.demo.category.domain.Category;
import com.deneme.demo.category.service.CategoryService;
import com.deneme.demo.category.service.dto.CategoryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category Controller")
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Schema(description = "Get Category List")
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getCategoriesList() {
        List<CategoryDto> categoryDtoList = this.categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @Schema(description = "Get Category By Id")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Schema(description = "Create Category")
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto> insertCategory(@RequestBody CategoryDto categoryDto) {
        categoryDto = this.categoryService.insertCategory(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @Schema(description = "Update Category")
    @PutMapping("/updateCategory")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryDto = this.categoryService.updateCategory(categoryDto);
        if (categoryDto != null) {
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Schema(description = "Delete Category")
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable Long id) {
        categoryService.removeCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Schema(description = "Delete All Categories")
    @DeleteMapping("/deleteAllCategories")
    public ResponseEntity<Void> removeAllCategories() {
        categoryService.removeAllCategories();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
