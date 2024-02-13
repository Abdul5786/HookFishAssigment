package com.Abdul.HookiFish.services;

import com.Abdul.HookiFish.dtos.CustomerDtos;
import com.Abdul.HookiFish.dtos.GeneratedBillDto;
import com.Abdul.HookiFish.entities.GenerateBill;
import com.Abdul.HookiFish.dtos.Units;

public interface CustomerService
{
    CustomerDtos addCustomer(CustomerDtos customerDtos);

//    List<CustomerDtos> listofCustomerWithoutProduct();
    CustomerDtos updateCustomer(CustomerDtos customerDtos,Long customerId);

    Boolean purchaseProduct(Integer productId,GeneratedBillDto generatedBillDto);

    GeneratedBillDto generateBill(GeneratedBillDto generateBill, Integer productId, Long customerId);

    GeneratedBillDto getBillReciept(Integer billId);

}
