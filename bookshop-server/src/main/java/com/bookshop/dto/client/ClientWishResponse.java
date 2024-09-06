package com.bookshop.dto.client;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
public class ClientWishResponse {
    private Long wishId;
    private Instant wishCreatedAt;
    private ClientListedProductResponse wishProduct;

    @Data
    public static class ClientListedProductResponse {
        private Long productId;
        private String productName;
        private List<ClientListedVariantResponse> productVariants;

        @Data
        public static class ClientListedVariantResponse {
            private Long variantId;
            private Double variantPrice;
        }
    }
}