package com.bookshop.dto.product;


import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String slug;
    @Nullable
    private String description;
    @Nullable
    private String thumbnail;
    private Integer status;
}
