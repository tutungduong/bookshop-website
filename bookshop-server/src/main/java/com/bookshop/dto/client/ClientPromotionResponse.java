package com.bookshop.dto.client;

import lombok.Data;

@Data
public class ClientPromotionResponse {
    private Long promotionId;
    private Integer promotionPercent;
}