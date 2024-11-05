package com.eidiko.super_bazar_sales.repository;

import com.eidiko.super_bazar_sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findByStoreId(int id);
}
