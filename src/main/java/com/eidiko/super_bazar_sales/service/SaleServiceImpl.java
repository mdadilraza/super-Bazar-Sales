package com.eidiko.super_bazar_sales.service;

import com.eidiko.super_bazar_sales.dto.SalesReports;
import com.eidiko.super_bazar_sales.entity.Sale;
import com.eidiko.super_bazar_sales.repository.ProductRepository;
import com.eidiko.super_bazar_sales.repository.SaleRepository;
import com.eidiko.super_bazar_sales.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private ProductRepository productRepository;
    private SaleRepository saleRepository;
    private StoreRepository storeRepository;

    @Override
    public List<SalesReports> getAllSalesByStoreId(int id) {
        List<Sale> sales = saleRepository.findByStoreId(id);


        //Functional Programming Approach
        return sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getProduct().getName())) // Group sales by product name
                .entrySet()
                .stream()
                .map(entry -> {
                    String productName = entry.getKey();
                    List<Sale> productSales = entry.getValue();

                    // Get the first sale to extract MRP and calculate the total sale quantity
                    Sale firstSale = productSales.get(0);
                    double mrp = firstSale.getProduct().getMrp();
                    int totalQuantity = productSales.stream().mapToInt(Sale::getSale_quantity).sum();
                    double totalAmount = totalQuantity * mrp;

                    return new SalesReports(productName, totalQuantity, mrp, totalAmount);
                })
                .toList();


//        //Traditional approach
//        List<SalesReports> reportList = new ArrayList<>();
//
//
//        for (Sale sale : sales) {
//            String productName = sale.getProduct().getName();
//            double mrp = sale.getProduct().getMrp();
//            int saleQuantity = sale.getSale_quantity();
//            double amount = saleQuantity * mrp;
//
//            boolean productExists = false;
//
//            // Check if product is already in reportList
//            for (SalesReports report : reportList) {
//                if (report.getProductName().equals(productName)) {
//                    // Update existing report with new quantity and amount
//                    report.setSale(report.getSale() + saleQuantity);
//                    report.setAmount(report.getAmount() + amount);
//                    productExists = true;
//                    break;
//                }
//            }
//
//            // If product not found in reportList, add a new entry
//            if (!productExists) {
//                reportList.add(new SalesReports(productName, saleQuantity, mrp, amount));
//            }
//        }
//
//        return reportList;

    }
}
