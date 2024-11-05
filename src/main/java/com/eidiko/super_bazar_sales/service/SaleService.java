package com.eidiko.super_bazar_sales.service;

import com.eidiko.super_bazar_sales.dto.SalesReports;

import java.util.List;

public interface SaleService {

    List<SalesReports> getAllSalesByStoreId(int id);
}
