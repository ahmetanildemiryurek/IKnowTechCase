package com.deneme.demo.product.service.impl;

import com.deneme.demo.product.domain.Product;
import com.deneme.demo.product.repository.ProductRepository;
import com.deneme.demo.product.service.ProductService;
import com.deneme.demo.product.service.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getId()).orElse(null);
        if (existingProduct != null) {
            BeanUtils.copyProperties(productDto, existingProduct);
            productRepository.save(existingProduct);
            return productDto;
        }
        return null;
    }

    @Override
    public ProductDto insertProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void removeAllProduct() {
        productRepository.deleteAll();
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
