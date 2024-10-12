package com.bookshop.controller.review;


import com.bookshop.constant.AppConstants;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.review.ReviewRequest;
import com.bookshop.dto.review.ReviewResponse;
import com.bookshop.service.review.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ListResponse<ReviewResponse>> getAllReviews(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "false") boolean all
    ) {
        ListResponse<ReviewResponse> response = reviewService.findAll(page, size, sort, filter, search, all);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        ReviewResponse response = reviewService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request) {
        ReviewResponse response = reviewService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequest request
    ) {
        ReviewResponse response = reviewService.save(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReviews(@RequestBody List<Long> ids) {
        reviewService.delete(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}