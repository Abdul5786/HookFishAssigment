package com.Abdul.HookiFish.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="productCategoriesTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategories
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productCategoriesName;
    @OneToMany(mappedBy = "productCategories",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> productList= new ArrayList<>();

}
