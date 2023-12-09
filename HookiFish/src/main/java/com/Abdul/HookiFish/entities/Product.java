package com.Abdul.HookiFish.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name="product_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productPrice;
    private Date buyingDate;
    @ManyToOne
    private Customer customer;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", buyingDate=" + buyingDate +
                ", customer=" + customer +
                '}';
    }
}
