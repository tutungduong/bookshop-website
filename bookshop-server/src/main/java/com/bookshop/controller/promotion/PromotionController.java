package com.bookshop.controller.promotion;

import com.bookshop.constant.AppConstants;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.promotion.PromotionCheckingResponse;
import com.bookshop.dto.promotion.PromotionRequest;
import com.bookshop.dto.promotion.PromotionResponse;
import com.bookshop.service.promotion.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/promotions")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping("/checking")
    public ResponseEntity<PromotionCheckingResponse> checkCanCreatePromotionForProduct(
            @RequestParam Long productId,
            @RequestParam Instant startDate,
            @RequestParam Instant endDate
    ) {
        boolean promotionable = promotionService.checkCanCreatePromotionForProduct(productId, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(new PromotionCheckingResponse(promotionable));
    }

    @GetMapping
    public ResponseEntity<ListResponse<PromotionResponse>> getAllPromotions(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "false") boolean all
    ) {
        ListResponse<PromotionResponse> response = promotionService.findAll(page, size, sort, filter, search, all);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> getPromotionById(@PathVariable Long id) {
        PromotionResponse response = promotionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<PromotionResponse> createPromotion(@RequestBody PromotionRequest request) {
        PromotionResponse response = promotionService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionResponse> updatePromotion(
            @PathVariable Long id,
            @RequestBody PromotionRequest request
    ) {
        PromotionResponse response = promotionService.save(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePromotions(@RequestBody List<Long> ids) {
        promotionService.delete(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
