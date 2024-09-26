package com.bookshop.dto.client;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ClientCartVariantResponse {
    private ClientVariantResponse cartItemVariant;
    private Integer cartItemQuantity;

    @Data
    public static class ClientVariantResponse {
        private Long variantId;
        private ClientProductResponse variantProduct;
        private Double variantPrice;
        private Double variantDiscount;

        @Data
        public static class ClientProductResponse {
            private Long productId;
            private String productName;
            private String productSlug;
            private String productAuthor;
            @Nullable
            private String productThumbnail;
            @Nullable
            private ClientPromotionResponse productPromotion;
        }
    }
}