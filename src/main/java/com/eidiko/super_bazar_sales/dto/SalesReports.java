package com.eidiko.super_bazar_sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesReports {
    private String productName;
    private int sale ;
    private double mrp ;
    private double amount;
}
