package com.dims.marketplace.dto.order.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private UUID orderId;
    private UUID productVariantId;
    private String productName;
    private String variantName;
    private BigDecimal price;
    private Integer quantity;
}