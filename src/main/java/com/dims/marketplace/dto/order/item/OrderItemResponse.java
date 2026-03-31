package com.dims.marketplace.dto.order.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private UUID id;
    private UUID orderId;
    private UUID variantId;
    private String productName;
    private String variantName;
    private BigDecimal price;
    private Integer quantity;
}
