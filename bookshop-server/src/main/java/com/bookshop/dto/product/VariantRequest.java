package com.bookshop.dto.product;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantRequest {
    @Nullable
    private Long productId;
    private Double price;
    private Double discount;
    private Integer status;
}
