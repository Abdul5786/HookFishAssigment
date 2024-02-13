package com.Abdul.HookiFish.entities;


import com.Abdul.HookiFish.entities.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "Generated_Bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenerateBill
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;
    private LocalDate billDate;
    private double totalAmount;
    private int units;
    private String productName;
    private double Gst;
    private double discount;
    private double grandTotal;
    private int productPrice;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
