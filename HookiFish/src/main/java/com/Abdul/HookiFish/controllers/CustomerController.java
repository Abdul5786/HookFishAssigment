package com.Abdul.HookiFish.controllers;

import com.Abdul.HookiFish.dtos.CustomerDtos;
import com.Abdul.HookiFish.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/add")
    public ResponseEntity<CustomerDtos> addCustomer(@RequestBody CustomerDtos  customerDtos)
    {
        CustomerDtos customer = customerService.addCustomer(customerDtos);
         return new ResponseEntity<>(customer,HttpStatus.CREATED);
    }
    @GetMapping(value = "/getcustomerwithoutproduct")
    public ResponseEntity<List<CustomerDtos>> customerWithOutProdut()
    {
        List<CustomerDtos> listofCustomerWithoutProduct = customerService.listofCustomerWithoutProduct();
        return new ResponseEntity<>(listofCustomerWithoutProduct,HttpStatus.FOUND);
    }

}
