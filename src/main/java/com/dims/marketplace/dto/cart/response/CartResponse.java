package com.dims.marketplace.dto.cart.response;

import com.dims.marketplace.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private UUID id;
    private User user;
    private LocalDateTime createdAt;
}
