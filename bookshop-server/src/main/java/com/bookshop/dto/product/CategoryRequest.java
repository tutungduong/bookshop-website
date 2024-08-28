package com.bookshop.dto.product;


import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String description;
//    private String thumbnail;
    private Integer status;
}
