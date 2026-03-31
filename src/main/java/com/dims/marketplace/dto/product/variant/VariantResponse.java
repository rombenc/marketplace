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
    private UUID productId;
    private String sku;
    private BigDecimal price;
    private LocalDateTime createdAt;
}
