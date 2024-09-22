package com.bookshop.dto.product;


import com.bookshop.dto.general.ImageResponse;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String slug;
    @Nullable
    private String shortDescription;
    @Nullable
    private String description;
    private List<ImageResponse> images;
    private String author;
    private String publisher;
    private Integer publishedYear;
    private Integer pages;
    private Integer status;
    @Nullable
    private ProductResponse.CategoryResponse category;
    private List<ProductResponse.VariantResponse> variants;
    private Instant createdAt;
    private Instant updatedAt;


    @Data
    public static class CategoryResponse{
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private String name;
        private String slug;
        @Nullable
        private String description;
        @Nullable
        private String thumbnail;
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
