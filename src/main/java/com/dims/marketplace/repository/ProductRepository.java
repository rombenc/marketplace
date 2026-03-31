package com.dims.marketplace.repository;

import com.dims.marketplace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
