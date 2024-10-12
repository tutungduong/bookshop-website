package com.bookshop.dto.product;


import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;

@Data
public class VariantResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private Double price;
    private Double discount;
    @Nullable
    private Integer status;
    private VariantResponse.ProductResponse product;
    @Data
    public static class ProductResponse {
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private String name;
        private String author;
        private String slug;
    }
}
