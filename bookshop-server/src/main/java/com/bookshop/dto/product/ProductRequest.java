package com.bookshop.dto.product;

import com.bookshop.dto.general.ImageRequest;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String slug;
    @Nullable
    private String shortDescription;
    @Nullable
    private String description;
    private List<ImageRequest> images;
    private String author;
    @Nullable
    private String publisher;
    private Integer publishedYear;
    @Nullable
    private Integer pages;
    @Nullable
    private Long categoryId;
    private Integer status;
    private List<VariantRequest> variants;
}
