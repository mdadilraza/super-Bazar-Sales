package com.eidiko.super_bazar_sales.service;

import com.eidiko.super_bazar_sales.dto.SalesReports;
import com.eidiko.super_bazar_sales.entity.Product;
import com.eidiko.super_bazar_sales.entity.Sale;
import com.eidiko.super_bazar_sales.repository.ProductRepository;
import com.eidiko.super_bazar_sales.repository.SaleRepository;
import com.eidiko.super_bazar_sales.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private ProductRepository productRepository;
    private SaleRepository saleRepository;
    private StoreRepository storeRepository;


    @Override
    public List<SalesReports> getAllSalesByStoreId(int id) {
         return productRepository.findAll().stream()
                .map(product -> {
                    int productSaleCount = saleRepository.findAll().stream()
                            .filter(sale -> sale.getStore().getId() == storeId)
                            .filter(prod -> prod.getProduct().getId() == product.getId())
                            .mapToInt(Sale::getSaleQuantity)
                            .sum();

                    log.info("productSaleCount {}", productSaleCount);

                    return SalesReports
                            .builder()
                            .productName(product.getName())
                            .saleQuantity(productSaleCount)
                            .mrp(product.getMrp())
                            .totalAmount(productSaleCount * product.getMrp())
                            .build();
                }).toList();
    }


//
//        //Traditional approach
//        List<SuperBazarSales> reportList = new ArrayList<>();
//
//
//// Step 1: Initialize reportList with all products (with zero quantity and amount)
//        for (Product product : products) {
//            reportList.add(new SuperBazarSales(product.getName(), 0, product.getMRP(), 0.0));
//        }
//
//// Step 2: Update reportList with actual sales data
//        for (Sale sale : sales) {
//            String productName = sale.getProduct().getName();
//            double mrp = sale.getProduct().getMRP();
//            int saleQuantity = sale.getSale_quantity();
//            double amount = saleQuantity * mrp;
//
//            // Find the product in reportList and update it
//            for (SuperBazarSales report : reportList) {
//                if (report.getName().equals(productName)) {
//                    report.setSale(report.getSale() + saleQuantity);
//                    report.setAmount(report.getAmount() + amount);
//                    break;
//                }
//            }
//        }
//
//        return reportList
// }


}
