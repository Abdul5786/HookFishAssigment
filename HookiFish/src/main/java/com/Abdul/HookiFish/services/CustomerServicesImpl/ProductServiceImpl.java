package com.Abdul.HookiFish.services.CustomerServicesImpl;


import com.Abdul.HookiFish.dtos.ProductDtos;
import com.Abdul.HookiFish.entities.Customer;
import com.Abdul.HookiFish.entities.Product;
import com.Abdul.HookiFish.exception.ResourceNotFoundException;
import com.Abdul.HookiFish.repositories.CustomerRepo;
import com.Abdul.HookiFish.repositories.ProductRepo;
import com.Abdul.HookiFish.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private ModelMapper modelMapper;
    @Override
    public ProductDtos addProduct(ProductDtos productDtos,Long customerId) {
        // fetching customer by id to set in product table
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customerId", "customerId", +customerId));
        System.out.println("customer details"+customer);
        Product product = this.modelMapper.map(productDtos, Product.class);
        product.setCustomer(customer); // setting customer
        product.setBuyingDate(new Date());
        Product saved = productRepo.save(product);// saving
        ProductDtos dtos = this.modelMapper.map(saved,ProductDtos.class);
        return dtos;

    }

    @Override
    public List<ProductDtos> listOfProduct()
    {

        List<Product> all = productRepo.findAll();
        List<ProductDtos> productDtosList = all.stream().map(p -> this.modelMapper.map(p, ProductDtos.class)).collect(Collectors.toList());
        return productDtosList;
    }



}
