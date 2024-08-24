package com.bookshop.dto.client;

import com.fasterxml.jackson.databind.JsonNode;
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
//            private String productThumbnail;
        }
    }
}