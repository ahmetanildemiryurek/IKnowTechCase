package com.deneme.demo.product.service.mapper;

import com.deneme.demo.product.domain.Product;
import com.deneme.demo.product.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto toProductDto(Product product);
    List<ProductDto> toProductDto(List<Product> product);
    Product toProduct(ProductDto productDto);
}
