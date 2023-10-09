package com.deneme.demo.product.domain;

import com.deneme.demo.category.domain.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;

    @ManyToOne
    private Category category;

}
