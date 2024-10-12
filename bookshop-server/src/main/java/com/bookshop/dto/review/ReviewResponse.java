package com.bookshop.dto.review;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.Instant;

@Data
public class ReviewResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private ReviewResponse.UserResponse user;
    private ReviewResponse.ProductResponse product;
    private Integer ratingScore;
    private String content;
    @Nullable
    private String reply;
    private Integer status;

    @Data
    public static class UserResponse {
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private String username;
        private String fullname;
    }

    @Data
    public static class ProductResponse {
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private String name;
    }
}