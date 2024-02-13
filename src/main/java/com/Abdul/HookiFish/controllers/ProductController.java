package com.Abdul.HookiFish.controllers;

import com.Abdul.HookiFish.dtos.ProductDtos;
import com.Abdul.HookiFish.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController
{
    @Autowired
    private ProductService productService;

//    @PostMapping(value = "/buy/customer/{customerId}")
//    public ResponseEntity<ProductDtos> buyProduct(@RequestBody ProductDtos productDtos,@PathVariable Long customerId)
//    {
//        ProductDtos addedProduct = productService.addProduct(productDtos,customerId);
//        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
//    }


      @PostMapping(value = "/addProduct/{productCatId}")
      public ResponseEntity<ProductDtos> addProduct(@RequestBody ProductDtos productDtos,@PathVariable Integer productCatId)
      {
          ProductDtos savedProduct = productService.addProduct(productDtos,productCatId);
          return  ResponseEntity.ok(savedProduct);
      }

      @GetMapping(value = "/getProductList")
      public ResponseEntity<List<ProductDtos>> getListOfProduct(@RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                                @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                                @RequestParam(value = "sortBy",defaultValue = "productName",required = false) String  sortBy)
      {
          List<ProductDtos> productDtosList = productService.listOfProduct(pageNumber,pageSize,sortBy);
          return  new ResponseEntity<>(productDtosList,HttpStatus.FOUND);
      }

//      @PutMapping(value = "/updateProductStocks/{productId}")
//      public ResponseEntity<ProductDtos> updateProductStock(@RequestBody QuantityDtos quantityDtos,@PathVariable Long productId)
//      {
//          ProductDtos productStockUpdated = productService.updateProductStocks(quantityDtos, productId);
//          return ResponseEntity.ok(productStockUpdated);
//
//      }

}
