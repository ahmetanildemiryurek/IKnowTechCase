package com.deneme.demo.product.service;

import com.deneme.demo.product.domain.Product;
import com.deneme.demo.product.service.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProduct();
    Product getProductById(Long id);
    ProductDto updateProduct(ProductDto productDto);
    ProductDto insertProduct(ProductDto productDto);
    void removeProduct(Long id);
    void removeAllProduct();


}
