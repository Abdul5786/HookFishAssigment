package com.Abdul.HookiFish.controllers;

import com.Abdul.HookiFish.dtos.ProductDtos;
import com.Abdul.HookiFish.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/buy/customer/{customerId}")
    public ResponseEntity<ProductDtos> buyProduct(@RequestBody ProductDtos productDtos,@PathVariable Long customerId)
    {
        ProductDtos addedProduct = productService.addProduct(productDtos,customerId);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }


}
