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
        List<Sale> sales = saleRepository.findByStoreId(id);
        List<Product> products = productRepository.findAll();

        // Initialize the report list with all products and set initial sales to 0
        List<SalesReports> bazarSales = products.stream()
                .map(prod -> new SalesReports(prod.getName(),
                        0,
                        prod.getMrp(),
                        0.0))
                .toList();
        log.info("bazarSales: {}", bazarSales);

        // Group sales by product name
        Map<String, List<Sale>> groupedSales = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getProduct().getName()));

        log.info("groupedSales: {}", groupedSales);

        // Update the report list with actual sales data
        groupedSales.forEach((productName, productSales) -> {
            // Calculate total quantity and amount for the product
            int totalQuantity = productSales.stream().
                    mapToInt(Sale::getSale_quantity).sum();
            double mrp = productSales.get(0).
                    getProduct().
                    getMrp();
            double totalAmount = totalQuantity * mrp;

            // Find the product in the report list and update the sale and amount
            bazarSales.stream()
                    .filter(report -> report.getProductName().equals(productName))
                    .findFirst()
                    .ifPresent(report -> {
                        report.setSale(totalQuantity);
                        report.setAmount(totalAmount);
                    });
        });

        return bazarSales;
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
