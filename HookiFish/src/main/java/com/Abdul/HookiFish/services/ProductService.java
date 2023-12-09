package com.Abdul.HookiFish.services;

import com.Abdul.HookiFish.dtos.ProductDtos;

import java.util.List;

public interface ProductService
{
    ProductDtos addProduct(ProductDtos productDtos,Long customerId);
    List<ProductDtos> listOfProduct();

}
