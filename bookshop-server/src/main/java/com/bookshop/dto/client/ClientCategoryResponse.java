package com.bookshop.dto.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientCategoryResponse {
    private String categoryName;
    private String categorySlug;
}