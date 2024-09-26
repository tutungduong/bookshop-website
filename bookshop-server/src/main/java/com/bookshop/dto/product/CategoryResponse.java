package com.bookshop.dto.product;


import lombok.Data;

import java.time.Instant;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String thumbnail;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer status;

}
