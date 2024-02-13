package com.Abdul.HookiFish.services.CustomerServicesImpl;

import com.Abdul.HookiFish.dtos.CustomerDtos;
import com.Abdul.HookiFish.dtos.GeneratedBillDto;
import com.Abdul.HookiFish.entities.GenerateBill;
import com.Abdul.HookiFish.dtos.ProductDtos;
import com.Abdul.HookiFish.dtos.Units;
import com.Abdul.HookiFish.entities.Customer;
import com.Abdul.HookiFish.entities.Product;
import com.Abdul.HookiFish.entities.ProductCategories;
import com.Abdul.HookiFish.exception.ResourceNotFoundException;
import com.Abdul.HookiFish.repositories.CustomerRepo;
import com.Abdul.HookiFish.repositories.GenerateBillRepo;
import com.Abdul.HookiFish.repositories.ProductRepo;
import com.Abdul.HookiFish.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private GenerateBillRepo generateBillRepo;

    @Override
    public CustomerDtos addCustomer(CustomerDtos customerDtos)
    {
        Customer customer = this.modelMapper.map(customerDtos, Customer.class);
        Customer saved = customerRepo.save(customer);
        return this.modelMapper.map(saved, CustomerDtos.class);
    }


    public CustomerDtos updateCustomer(CustomerDtos customerDtos, Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", +customerId));
        customer.setName(customerDtos.getName()); // updating
        customer.setEmail(customerDtos.getEmail());
        customer.setContactNo(customerDtos.getContactNo());
        return this.modelMapper.map(customer, CustomerDtos.class);
    }

    @Override
    public Boolean purchaseProduct(Integer productId, GeneratedBillDto generatedBillDto)
    {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product", "productId", +productId));
        Boolean avalableProduct;
        int avalableProductUnits = product.getUnits();
        if (generatedBillDto.getUnits() <= avalableProductUnits) {

            int remainingUnits = avalableProductUnits - generatedBillDto.getUnits();
            int purchasingInventoryPrice = generatedBillDto.getUnits() * product.getProductPrice();
            product.setTotalInventoryPrice(product.getTotalInventoryPrice() - purchasingInventoryPrice);
            product.setUnits(remainingUnits);
            productRepo.save(product);

            this.modelMapper.map(product, ProductDtos.class);
            return avalableProduct = true;
        } else {
            return avalableProduct = false;
        }


    }

    @Override
    public GeneratedBillDto generateBill(GeneratedBillDto generatedBillDto, Integer productId, Long customerId)
    {
        GenerateBill generateBill = this.modelMapper.map(generatedBillDto, GenerateBill.class);
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Billing product ", "productId", +productId));
        ProductCategories productCategories = product.getProductCategories();
        String productCategorieName = productCategories.getProductCategoriesName();
        System.out.println(productCategorieName);
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", +customerId));
        generateBill.setBillDate(LocalDate.now());
        generateBill.setProductName(product.getProductName());
        // calculating the bill
        int totalAmount = product.getProductPrice() * generatedBillDto.getUnits();
        double tax = totalAmount * generatedBillDto.getGst() / 100; // tax
        double discount = totalAmount * product.getDiscount() / 100; // discount on product
        double grandTotal = totalAmount + tax - discount;// grand total
        // setting the details
        generateBill.setProductPrice(product.getProductPrice());
        generateBill.setTotalAmount(totalAmount);
        generateBill.setGst(tax);
        generateBill.setDiscount(discount);
        generateBill.setGrandTotal(grandTotal);
        generateBill.setCustomer(customer);
        generateBillRepo.save(generateBill);
        generatedBillDto.setBillId(String.format("bill is %s", productCategorieName + generateBill.getBillId()));
        System.out.println(productCategorieName + generateBill.getBillId());
        return this.modelMapper.map(generateBill, GeneratedBillDto.class);


    }

    @Override
    public GeneratedBillDto getBillReciept(Integer billId) {
        GenerateBill generateBill = generateBillRepo.findById(billId).orElseThrow(() -> new ResourceNotFoundException("bill Not Found", "BillId", +billId));
        return this.modelMapper.map(generateBill, GeneratedBillDto.class);
    }

    @Override
    public List<CustomerDtos> getAllCustomer()
    {
          List<Customer> allCustomerList = customerRepo.findAll();
          List<CustomerDtos> customerDtosList = allCustomerList.stream().map(c -> this.modelMapper.map(c, CustomerDtos.class)).collect(Collectors.toList());
           return customerDtosList;



    }
}

