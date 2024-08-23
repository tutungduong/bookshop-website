package com.bookshop.dto.product;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
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
    @Nullable
    private Long categoryId;
    private Integer status;
//    private List<VariantRequest> variants;
}
