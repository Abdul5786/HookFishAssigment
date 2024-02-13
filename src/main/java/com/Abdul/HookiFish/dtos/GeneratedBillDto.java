package com.Abdul.HookiFish.dtos;

import com.Abdul.HookiFish.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedBillDto
{
    private String billId;
    private LocalDate billDate;
    private double totalAmount;
    private int units;
    private String productName;
    private double Gst;
    private double discount;
    private double grandTotal;
    private int productPrice;
    private CustomerDtos customer;

}
