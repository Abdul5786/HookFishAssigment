package com.Abdul.HookiFish.services;

import com.Abdul.HookiFish.dtos.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryServices
{
    ProductCategoryDto addProductCategories(ProductCategoryDto productCategoryDto); // to add product categories
    List<ProductCategoryDto> getAllProductCategoriesList(Integer pageNumber,Integer pageSize,String sortBy); // to get the all list of product
}
