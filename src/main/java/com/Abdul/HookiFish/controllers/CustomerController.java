package com.Abdul.HookiFish.controllers;

import com.Abdul.HookiFish.dtos.CustomerDtos;
import com.Abdul.HookiFish.dtos.GeneratedBillDto;
import com.Abdul.HookiFish.entities.GenerateBill;
import com.Abdul.HookiFish.dtos.PdfGenerator;
import com.Abdul.HookiFish.dtos.Units;
import com.Abdul.HookiFish.exception.ResourceNotFoundException;
import com.Abdul.HookiFish.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private PdfGenerator pdfGenerator;
    @PostMapping(value = "/add")
    public ResponseEntity<CustomerDtos> addCustomer(@RequestBody CustomerDtos  customerDtos)
    {
        CustomerDtos customer = customerService.addCustomer(customerDtos);
         return new ResponseEntity<>(customer,HttpStatus.CREATED);
    }
       @GetMapping(value = "purchaseProducts/{productId}/{customerId}")
      public ResponseEntity<?> purchaseProduct(@PathVariable Integer productId,@PathVariable Long customerId, @RequestBody GeneratedBillDto  generatedBillDto)
      {
          Boolean availableProduct = customerService.purchaseProduct(productId, generatedBillDto);
          if (availableProduct)
          {
              GeneratedBillDto savedGeneratedBillDto = customerService.generateBill(generatedBillDto, productId, customerId);
              return new ResponseEntity<>(savedGeneratedBillDto,HttpStatus.CREATED);
          }

          else {
                   throw new ResourceNotFoundException("Product not Available","available  units ", +generatedBillDto.getUnits());
               }


      }

      @PostMapping(value = "/generatedPdf/{billId}")
      public ResponseEntity<byte[]> generatePdf(@PathVariable Integer billId) throws IOException
      {
          GeneratedBillDto billReciept = customerService.getBillReciept(billId);
          byte[] generatedPdf = PdfGenerator.generatePdf(billReciept);
          HttpHeaders httpHeaders = new HttpHeaders();
          httpHeaders.setContentType(MediaType.APPLICATION_PDF);
          httpHeaders.setContentDispositionFormData("filename","bill.pdf");
          return new ResponseEntity<>(generatedPdf,httpHeaders,HttpStatus.OK);
      }

          @GetMapping(value = "/getAllCustomerList")
      public ResponseEntity<List<CustomerDtos>> getAllCustomerList()
      {
          List<CustomerDtos> allCustomerList = customerService.getAllCustomer();
          return new ResponseEntity<>(allCustomerList,HttpStatus.FOUND);
      }













}
