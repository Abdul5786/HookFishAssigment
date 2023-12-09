package com.Abdul.HookiFish.services.CustomerServicesImpl;

import com.Abdul.HookiFish.dtos.CustomerDtos;
import com.Abdul.HookiFish.entities.Customer;
import com.Abdul.HookiFish.exception.ResourceNotFoundException;
import com.Abdul.HookiFish.repositories.CustomerRepo;
import com.Abdul.HookiFish.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CustomerDtos addCustomer(CustomerDtos customerDtos)
    {
        Customer customer = this.modelMapper.map(customerDtos, Customer.class);
        Customer saved = customerRepo.save(customer);
        return this.modelMapper.map(saved,CustomerDtos.class);
    }

    @Override
    public List<CustomerDtos> listofCustomerWithoutProduct()
    {
        // fetching list of customers
        List<Customer> all = customerRepo.findAll();
        // using stream apis to find the customer who have 0 products
        // using isEmpty function
        List<Customer> customerWithoutList = all.stream().filter(customer -> customer.getProducts().isEmpty()).collect(Collectors.toList());
        List<CustomerDtos> customerWithoutProduct = customerWithoutList.stream().map(customer -> this.modelMapper.map(customer, CustomerDtos.class)).collect(Collectors.toList());
        return customerWithoutProduct;
    }

    @Override
    public CustomerDtos updateCustomer(CustomerDtos customerDtos, Long customerId)
    {
        Customer customer = customerRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("customer","customerId",+customerId));
        customer.setName(customerDtos.getName()); // updating
        customer.setEmail(customerDtos.getEmail());
        customer.setContactNo(customerDtos.getContactNo());
        return this.modelMapper.map(customer,CustomerDtos.class);
    }
}
