package com.eidiko.super_bazar_sales.controller;

import com.eidiko.super_bazar_sales.dto.SalesReports;
import com.eidiko.super_bazar_sales.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SaleController {

    private SaleService saleService;

    @GetMapping("/{storeId}")
    public ResponseEntity<List<SalesReports>> getAllSalesByStoreId(@PathVariable int storeId){

       return ResponseEntity.ok( saleService.getAllSalesByStoreId(storeId));
    }
}
