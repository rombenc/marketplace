package com.dims.marketplace.dto.product.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ProductVariantRequest {
    @NotNull(message = "Product ID is required!")
    private UUID productId;
    @NotBlank(message = "SKU is required!")
    private String sku;
    @NotNull(message = "Price is required!")
    @Positive(message = "Price must be greater than 0!")
    private BigDecimal price;
}
