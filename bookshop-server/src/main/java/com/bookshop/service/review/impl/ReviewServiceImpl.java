package com.bookshop.service.review.impl;

import com.bookshop.constant.FieldName;
import com.bookshop.constant.ResourceName;
import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.review.ReviewRequest;
import com.bookshop.dto.review.ReviewResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.product.Product;
import com.bookshop.entity.review.Review;
import com.bookshop.exception.ResourceNotFoundException;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.product.ProductRepository;
import com.bookshop.repository.review.ReviewRepository;
import com.bookshop.service.review.ReviewService;
import com.bookshop.utils.SearchUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ListResponse<ReviewResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Review> sortable = RSQLJPASupport.toSort(sort);
        Specification<Review> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Review> searchable = SearchUtils.parse(search, SearchFields.PRODUCT);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<Review> entities = reviewRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<ReviewResponse> entityResponse = entities.getContent().stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
    }

    @Override
    public ReviewResponse findById(Long id) {
        return reviewRepository.findById(id)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public ReviewResponse save(ReviewRequest request) {
        Review review = requestToEntity(request);
        review = reviewRepository.save(review);
        return entityToResponse(review);
    }

    @Override
    public ReviewResponse save(Long id, ReviewRequest request)    {
        return reviewRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(reviewRepository::save)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        reviewRepository.deleteAllById(ids);
    }

    private Review requestToEntity(ReviewRequest request) {
       Review review = new Review();
         review.setRatingScore(request.getRatingScore());
         review.setContent(request.getContent());
         review.setReply(request.getReply());
         review.setStatus(request.getStatus());

         User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.ID, request.getUserId()));
         review.setUser(user);

         Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException(ResourceName.PRODUCT, FieldName.ID, request.getProductId()));
         review.setProduct(product);
         return review;
    }

    private Review partialUpdate(Review review, ReviewRequest request) {
         review.setRatingScore(request.getRatingScore());
         review.setContent(request.getContent());
         review.setReply(request.getReply());
         review.setStatus(request.getStatus());

         User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.ID, request.getUserId()));
         review.setUser(user);

         Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException(ResourceName.PRODUCT, FieldName.ID, request.getProductId()));
         review.setProduct(product);
         return review;
    }

    private ReviewResponse entityToResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setCreatedAt(review.getCreatedAt());
        response.setUpdatedAt(review.getUpdatedAt());
        response.setRatingScore(review.getRatingScore());
        response.setContent(review.getContent());
        response.setReply(review.getReply());
        response.setStatus(review.getStatus());

        ReviewResponse.UserResponse userResponse = new ReviewResponse.UserResponse();
        userResponse.setId(review.getUser().getId());
        userResponse.setCreatedAt(review.getUser().getCreatedAt());
        userResponse.setUpdatedAt(review.getUser().getUpdatedAt());
        userResponse.setUsername(review.getUser().getUsername());
        userResponse.setFullname(review.getUser().getFullname());
        response.setUser(userResponse);

        ReviewResponse.ProductResponse productResponse = new ReviewResponse.ProductResponse();
        productResponse.setId(review.getProduct().getId());
        productResponse.setCreatedAt(review.getProduct().getCreatedAt());
        productResponse.setUpdatedAt(review.getProduct().getUpdatedAt());
        productResponse.setName(review.getProduct().getName());
        response.setProduct(productResponse);

        return response;

    }
}
