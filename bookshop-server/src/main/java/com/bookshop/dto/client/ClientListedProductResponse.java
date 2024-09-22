package com.bookshop.dto.client;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ClientListedProductResponse {
    private Long productId;
    private String productName;
    private String productSlug;
    private String productAuthor;
    @Nullable
    private String productThumbnail;
    private List<ClientListedVariantResponse> productVariants;
    @Nullable
    private ClientPromotionResponse productPromotion;

    @Data
    @Accessors(chain = true)
    public static class ClientListedVariantResponse {
        private Long variantId;
        private Double variantPrice;
    }
}