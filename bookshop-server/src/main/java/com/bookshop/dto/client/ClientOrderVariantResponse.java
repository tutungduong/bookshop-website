package com.bookshop.dto.client;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientOrderVariantResponse {
    private ClientVariantResponse orderItemVariant;
    private BigDecimal orderItemPrice;
    private Integer orderItemQuantity;
    private BigDecimal orderItemAmount;

    @Data
    public static class ClientVariantResponse {
        private Long variantId;
        private ClientProductResponse variantProduct;


        @Data
        public static class ClientProductResponse {
            private Long productId;
            private String productName;
            private String productSlug;
            @Nullable
            private String productThumbnail;
            private boolean productIsReviewed;
        }
    }
}