package com.bookshop.dto.product;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String description;
//    private String thumbnail;
    private Integer status;
}
