package com.deneme.demo.product.web.controller;

import com.deneme.demo.category.domain.Category;
import com.deneme.demo.category.service.CategoryService;
import com.deneme.demo.product.domain.Product;
import com.deneme.demo.product.service.ProductService;
import com.deneme.demo.product.service.dto.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name= "Product Controller")
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;
    private  final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Schema(description = "Get Product List")
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getProductsList(){
        List<ProductDto> productDtoList = this.productService.getAllProduct();
        return  new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @Schema(description = "Get Product By Id")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        @PostMapping("/createProduct")
        public ResponseEntity<ProductDto> createProduct(
                @RequestParam("name") String name,
                @RequestParam("price") Double price,
                @RequestParam("categoryId") Long categoryId) {
            Category category = categoryService.getCategoryById(categoryId);

            if (category != null) {
                ProductDto productDto = new ProductDto();
                productDto.setName(name);
                productDto.setPrice(price);
                productDto.setCategory(category);

                productDto = productService.insertProduct(productDto);

                return new ResponseEntity<>(productDto, HttpStatus.CREATED);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @Schema(description = "Update Product")
    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDto>updateProduct(ProductDto productDto){
        productDto = this.productService.updateProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @Schema(description = "Delete Product")
    @DeleteMapping("/deleteProduct")
    public void removeProduct(Long id){
        this.productService.removeProduct(id);
    }

    @DeleteMapping("/deleteAllProducts")
    public void removeAllProducts(){
        this.productService.removeAllProduct();
    }



}
