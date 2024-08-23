package com.bookshop.dto.product;


import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;

@Data
public class ProductResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String author;
    @Nullable
    private String publisher;
    private Integer publishedYear;
    private Integer pages;
    private Integer status;
    @Nullable
    private ProductResponse.CategoryResponse category;

    @Data
    public static class CategoryResponse {
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private String name;
        @Nullable
        private String description;
        private Integer status;
    }

}
