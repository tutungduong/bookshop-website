package com.bookshop.dto.client;

import com.bookshop.dto.general.ImageResponse;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ClientProductResponse {
    private Long productId;
    private String productName;
    private String productSlug;
    @Nullable
    private String productShortDescription;
    @Nullable
    private String productDescription;
    private List<ImageResponse> productImages;
    @Nullable
    private ClientCategoryResponse productCategory;
    private List<ClientVariantResponse> productVariants;
    private int productAverageRatingScore;
    private int productCountReviews;
    private List<ClientListedProductResponse> productRelatedProducts;
    @Nullable
    private ClientPromotionResponse productPromotion;

    @Data
    @Accessors(chain = true)
    public static class ClientVariantResponse {
        private Long variantId;
        private Double variantPrice;
    }
}