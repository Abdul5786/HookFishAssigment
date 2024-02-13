package com.Abdul.HookiFish.services.CustomerServicesImpl;


import com.Abdul.HookiFish.dtos.ProductDtos;
import com.Abdul.HookiFish.dtos.Units;
import com.Abdul.HookiFish.entities.Product;
import com.Abdul.HookiFish.entities.ProductCategories;
import com.Abdul.HookiFish.exception.ResourceNotFoundException;
import com.Abdul.HookiFish.repositories.CustomerRepo;
import com.Abdul.HookiFish.repositories.ProductCategoryRepo;
import com.Abdul.HookiFish.repositories.ProductRepo;
import com.Abdul.HookiFish.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
     private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDtos addProduct(ProductDtos productDtos,Integer productCatId)
    {
          Product product = this.modelMapper.map(productDtos, Product.class);
          ProductCategories productCat = productCategoryRepo.findById(productCatId).orElseThrow(() -> new ResourceNotFoundException("product Not Found","productId",+productCatId));
          product.setProductCategories(productCat);
          Product savedproduct = productRepo.save(product);
          System.out.println(savedproduct);
          return this.modelMapper.map(savedproduct,ProductDtos.class);
    }

    @Override
    public List<ProductDtos> listOfProduct(Integer pageNumber,Integer pageSize,String sortBy)
    {

        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());// pagination
        Page<Product> allPost = productRepo.findAll(pageable);
        List<Product> all = allPost.getContent();
        List<ProductDtos> productDtosList = all.stream().map(p -> this.modelMapper.map(p, ProductDtos.class)).collect(Collectors.toList());
        return productDtosList;
    }

     @Override
    public ProductDtos updateProductStocks(Units prductUnits, Integer productId)
    {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product", "productId", +productId));
        int availableUnits = product.getUnits(); // available units in db
        product.setUnits(availableUnits+prductUnits.getUnits()); // updated Units
        productRepo.save(product);
        return this.modelMapper.map(product,ProductDtos.class);
    }




}




