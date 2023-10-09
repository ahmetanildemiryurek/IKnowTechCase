package com.deneme.demo.category.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String description;
    private String name;
}
