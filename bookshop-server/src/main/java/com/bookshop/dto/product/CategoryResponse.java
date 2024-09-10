package com.bookshop.dto.product;


import lombok.Data;

import java.time.Instant;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String thumbnail;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer status;
}
