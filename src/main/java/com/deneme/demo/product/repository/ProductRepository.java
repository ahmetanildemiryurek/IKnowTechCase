package com.deneme.demo.product.repository;

import com.deneme.demo.category.domain.Category;
import com.deneme.demo.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory(Category category);


}
