package com.Abdul.HookiFish.services;

import com.Abdul.HookiFish.dtos.CustomerDtos;

import java.util.List;

public interface CustomerService
{
    CustomerDtos addCustomer(CustomerDtos customerDtos);

    List<CustomerDtos> listofCustomerWithoutProduct();
    CustomerDtos updateCustomer(CustomerDtos customerDtos,Long customerId);
}
