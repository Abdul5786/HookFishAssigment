package com.Abdul.HookiFish.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String  productName;
    private int  productPrice;
    private Integer units;
    private String  brand;
    private Double discount;
    private int totalInventoryPrice;
    @ManyToOne
    @JoinColumn(name="productCategoryId")
    private ProductCategories productCategories;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", units=" + units +
                ", brand='" + brand + '\'' +
                ", productCategories=" + productCategories +
                '}';
    }
}
