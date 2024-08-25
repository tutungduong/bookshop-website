package com.bookshop.dto.client;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Set;

@Data
public class ClientCartRequest {
    @Nullable
    private Long cartId;
    private Long userId;
    private Set<ClientCartVariantRequest> cartItems;
    private Integer status;
    private UpdateQuantityType updateQuantityType;
}