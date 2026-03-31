package com.dims.marketplace.dto.cart.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private UUID id;
    private UUID cartId;
    private UUID variantId;
    private Integer quantity;
    private LocalDateTime createdAt;
}
