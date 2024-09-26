package com.bookshop.dto.review;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ReviewRequest {
    private Long userId;
    private Long productId;
    private Integer ratingScore;
    private String content;
    @Nullable
    private String reply;
    private Integer status;
}