package com.bookshop.dto.product;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String author;
    @Nullable
    private String publisher;
    private Integer publishedYear;
    private Integer pages;
    @Nullable
    private Long categoryId;
    private Integer status;
}
