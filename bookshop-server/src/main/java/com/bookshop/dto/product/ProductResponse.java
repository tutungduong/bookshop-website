package com.bookshop.dto.product;


import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String author;
    @Nullable
    private String publisher;
    @Nullable
    private Integer publishedYear;
    @Nullable
    private Integer pages;
    private Integer status;
    @Nullable
    private ProductResponse.CategoryResponse category;
    private List<ProductResponse.VariantResponse> variants;
    private Instant createdAt;
    private Instant updatedAt;

    @Data
    public static class CategoryResponse {
        private Long id;
        private String name;
        @Nullable
        private String description;
//        @Nullable
//        private String thumbnail;
        private Instant createdAt;
        private Instant updatedAt;
        private Integer status;
    }

    @Data
    public static class VariantResponse{
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private Double price;
        private Double discount;
        private Integer status;
    }

}
