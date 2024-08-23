package com.bookshop.dto.product;


import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;

@Data
public class CategoryResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    @Nullable
    private String description;
    private Integer status;
}
