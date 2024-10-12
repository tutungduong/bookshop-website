package com.bookshop.dto.client;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class ClientReviewResponse {
    private Long reviewId;
    private Instant reviewCreatedAt;
    private Instant reviewUpdatedAt;
    private ClientListedProductResponse reviewProduct;
    private Integer reviewRatingScore;
    private String reviewContent;
    @Nullable
    private String reviewReply;
    private Integer reviewStatus;
}