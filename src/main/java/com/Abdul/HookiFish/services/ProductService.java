package com.Abdul.HookiFish.services;

import com.Abdul.HookiFish.dtos.ProductDtos;
import com.Abdul.HookiFish.dtos.Units;

import java.util.List;

public interface ProductService
{
    ProductDtos addProduct(ProductDtos productDtos,Integer productCatId);

    List<ProductDtos> listOfProduct(Integer pageNumber,Integer pageSize, String sortBy);

    ProductDtos updateProductStocks(Units units, Integer productId);



}
