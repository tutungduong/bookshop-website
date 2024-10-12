package com.bookshop.service.review;

import com.bookshop.dto.review.ReviewRequest;
import com.bookshop.dto.review.ReviewResponse;
import com.bookshop.service.CrudService;

public interface ReviewService extends CrudService<Long, ReviewRequest, ReviewResponse> {

}

