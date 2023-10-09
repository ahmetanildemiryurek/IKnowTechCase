package com.deneme.demo.product.service.dto;

import com.deneme.demo.category.domain.Category;
import com.deneme.demo.category.service.dto.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Category category;

    public ProductDto(Long id, String name, Double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

