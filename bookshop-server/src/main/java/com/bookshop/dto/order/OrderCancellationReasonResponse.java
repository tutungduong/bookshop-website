package com.bookshop.dto.order;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;

@Data
public class OrderCancellationReasonResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    @Nullable
    private String note;
    private Integer status;
}