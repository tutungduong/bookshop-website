package com.bookshop.service.promotion.impl;

import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.product.ProductResponse;
import com.bookshop.dto.promotion.PromotionRequest;
import com.bookshop.dto.promotion.PromotionResponse;
import com.bookshop.entity.product.Product;
import com.bookshop.entity.promotion.Promotion;
import com.bookshop.repository.product.ProductRepository;
import com.bookshop.repository.promotion.PromotionRepository;
import com.bookshop.service.promotion.PromotionService;
import com.bookshop.utils.SearchUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class PromotionServiceImpl  implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    @Override
    public boolean checkCanCreatePromotionForProduct(Long productId, Instant startDate, Instant endDate) {
         List<Promotion> promotions = promotionRepository.findByProductId(productId, startDate, endDate);
         return promotions.size() == 0;
    }

    @Override
    public ListResponse<PromotionResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Promotion> sortable = RSQLJPASupport.toSort(sort);
        Specification<Promotion> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Promotion> searchable = SearchUtils.parse(search, SearchFields.PRODUCT);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<Promotion> entities = promotionRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<PromotionResponse> entityResponse = entities.getContent().stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
    }

    @Override
    public PromotionResponse findById(Long id) {
        return promotionRepository.findById(id)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public PromotionResponse save(PromotionRequest request) {
        Promotion promotion = requestToEntity(request);
        promotion = promotionRepository.save(promotion);
        return entityToResponse(promotion);
    }

    @Override
    public PromotionResponse save(Long id, PromotionRequest request) {
        return promotionRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(promotionRepository::save)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        promotionRepository.deleteAllById(ids);
    }

    private Promotion requestToEntity(PromotionRequest request) {
        Promotion response = new Promotion();
        response.setName(request.getName());
        response.setStartDate(request.getStartDate());
        response.setEndDate(request.getEndDate());
        response.setPercent(request.getPercent());
        response.setStatus(request.getStatus());

        Product product = productRepository.findById(request.getProductIds().iterator().next()).get();
        product.setPromotions(Set.of(response));
        response.setProducts(Set.of(product));

        return response;
    }

    private Promotion partialUpdate(Promotion promotion, PromotionRequest request) {

        promotion.setName(request.getName());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setPercent(request.getPercent());
        promotion.setStatus(request.getStatus());
        promotion.setProducts(request.getProductIds().stream().map(productId -> {
            Product product = new Product();
            product.setId(productId);
            return product;
        }).collect(Collectors.toSet()));


        return promotion;

    }
    private PromotionResponse entityToResponse(Promotion entity) {

        PromotionResponse response = new PromotionResponse();
        response.setId(entity.getId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setName(entity.getName());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setPercent(entity.getPercent());
        response.setStatus(entity.getStatus());

        Set<ProductResponse> productResponses = entity.getProducts().stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setName(product.getName());
                    productResponse.setDescription(product.getDescription());
                    productResponse.setAuthor(product.getAuthor());
                    productResponse.setPublisher(product.getPublisher());
                    productResponse.setPublishedYear(product.getPublishedYear());
                    productResponse.setPages(product.getPages());
                    productResponse.setStatus(product.getStatus());
                    productResponse.setCreatedAt(product.getCreatedAt());
                    productResponse.setUpdatedAt(product.getUpdatedAt());

                     ProductResponse.CategoryResponse  categoryResponse = new ProductResponse.CategoryResponse();
                    categoryResponse.setId(product.getCategory().getId());
                    categoryResponse.setName(product.getCategory().getName());
                    categoryResponse.setDescription(product.getCategory().getDescription());
                    categoryResponse.setCreatedAt(product.getCategory().getCreatedAt());
                    categoryResponse.setUpdatedAt(product.getCategory().getUpdatedAt());
                    categoryResponse.setStatus(product.getCategory().getStatus());

                    productResponse.setCategory(categoryResponse);

                    productResponse.setVariants(product.getVariants().stream()
                            .map(variant -> {
                                ProductResponse.VariantResponse variantResponse = new ProductResponse.VariantResponse();
                                variantResponse.setId(variant.getId());
                                variantResponse.setCreatedAt(variant.getCreatedAt());
                                variantResponse.setUpdatedAt(variant.getUpdatedAt());
                                variantResponse.setPrice(variant.getPrice());
                                variantResponse.setDiscount(variant.getDiscount());
                                variantResponse.setStatus(variant.getStatus());
                                return variantResponse;
                            }).collect(Collectors.toList()));

                    return productResponse;
                }).collect(Collectors.toSet());

        response.setProducts(productResponses);

        return response;
    }
}
