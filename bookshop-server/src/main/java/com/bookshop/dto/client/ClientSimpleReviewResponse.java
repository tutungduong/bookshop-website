package com.bookshop.dto.client;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class ClientSimpleReviewResponse {
    private Long reviewId;
    private Instant reviewCreatedAt;
    private Instant reviewUpdatedAt;
    private ClientSimpleReviewResponse.UserResponse reviewUser;
    private Integer reviewRatingScore;
    private String reviewContent;
    @Nullable
    private String reviewReply;
    private Integer reviewStatus;

    @Data
    @Accessors(chain = true)
    public static class UserResponse {
        private Long userId;
        private String userUsername;
        private String userFullname;
    }
}