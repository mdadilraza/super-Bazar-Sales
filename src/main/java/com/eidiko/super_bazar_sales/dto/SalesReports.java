package com.eidiko.super_bazar_sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesReports {
    private String productName;
    private int sale ;
    private double mrp ;
    private double amount;
}
