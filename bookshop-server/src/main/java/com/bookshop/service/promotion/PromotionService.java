package com.bookshop.service.promotion;

import com.bookshop.dto.promotion.PromotionRequest;
import com.bookshop.dto.promotion.PromotionResponse;
import com.bookshop.service.CrudService;

import java.time.Instant;

public interface PromotionService extends CrudService<Long, PromotionRequest, PromotionResponse> {

    boolean checkCanCreatePromotionForProduct(Long productId, Instant startDate, Instant endDate);

}