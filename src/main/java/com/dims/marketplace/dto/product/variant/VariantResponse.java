package com.dims.marketplace.dto.product.variant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VariantResponse {
    private UUID id;
    private String sku;
    private String size;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createdAt;
}