package com.eidiko.super_bazar_sales.repository;

import com.eidiko.super_bazar_sales.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product ,Integer> {
}
