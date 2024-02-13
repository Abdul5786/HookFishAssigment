package com.Abdul.HookiFish.controllers;

import com.Abdul.HookiFish.dtos.ProductCategoryDto;
import com.Abdul.HookiFish.services.ProductCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productCategory")
public class ProductCategoryController
{
    @Autowired
    private ProductCategoryServices productCategoryServices;

    @PostMapping(value = "/addProductCategory")
    public ResponseEntity<ProductCategoryDto> addProductCategory(@RequestBody ProductCategoryDto productCategoryDto)
    {
        ProductCategoryDto savedProCatDto = productCategoryServices.addProductCategories(productCategoryDto);
        return new ResponseEntity<>(savedProCatDto, HttpStatus.FOUND);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ProductCategoryDto>> getAllProductCategory(
            @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam (value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
            @RequestParam (value = "sortBy",defaultValue = "productCategoriesName",required = false) String sortBy

    )
    {
        List<ProductCategoryDto> allProductCategoriesList = productCategoryServices.getAllProductCategoriesList(pageNumber,pageSize,sortBy);
        System.out.println(allProductCategoriesList);
        return ResponseEntity.ok(allProductCategoriesList);
    }

}
