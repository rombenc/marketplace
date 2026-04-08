package com.dims.marketplace.repository;

import com.dims.marketplace.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductVariantRepository extends JpaRepository<Variant, UUID> {
    boolean existsBySku(String sku);
}
