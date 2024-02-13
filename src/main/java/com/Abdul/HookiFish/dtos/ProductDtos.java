package com.Abdul.HookiFish.dtos;

import com.Abdul.HookiFish.entities.Customer;
import com.Abdul.HookiFish.entities.ProductCategories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtos
{
    private Long    productId;
    private String  productName;
    private String  productPrice;
    private Integer units;
    private String  brand;
    private int totalInventoryPrice;
    private ProductCategoryDto productCategories;

}
