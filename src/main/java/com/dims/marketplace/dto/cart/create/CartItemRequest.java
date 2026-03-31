package com.dims.marketplace.dto.cart.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private UUID id;
    private UUID cartId;
    private UUID productVariantId;
    private UUID quantityId;

}
