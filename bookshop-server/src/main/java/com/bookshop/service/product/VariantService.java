package com.bookshop.service.product;


import com.bookshop.constant.FieldName;
import com.bookshop.constant.ResourceName;
import com.bookshop.dto.product.VariantRequest;
import com.bookshop.dto.product.VariantResponse;
import com.bookshop.entity.product.Product;
import com.bookshop.entity.product.Variant;
import com.bookshop.exception.ResourceNotFoundException;
import com.bookshop.repository.product.ProductRepository;
import com.bookshop.repository.product.VariantRepository;
import com.bookshop.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VariantService implements CrudService<Long, VariantRequest, VariantResponse> {

    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;

    @Override
    public List<VariantResponse> findAll() {
       return variantRepository.findAll().stream()
               .map(this::mapToResponse)
               .collect(Collectors.toList());
    }

    @Override
    public VariantResponse findById(Long id) {
        return variantRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public VariantResponse save(VariantRequest request) {
       Variant variant = mapToEntity(request);
       variant = variantRepository.save(variant);
         return mapToResponse(variant);
    }

    @Override
    public VariantResponse save(Long aLong, VariantRequest request) {
        return variantRepository.findById(aLong)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(variantRepository::save)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        variantRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        variantRepository.deleteAllById(ids);
    }

    private Variant mapToEntity(VariantRequest request) {
        Variant variant = new Variant();
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ResourceName.PRODUCT, FieldName.ID, request.getProductId()));
            variant.setProduct(product);
        }
        variant.setPrice(request.getPrice());
        variant.setDiscount(request.getDiscount());
        variant.setStatus(request.getStatus());
        return variant;
    }

    private Variant partialUpdate(Variant variant, VariantRequest request) {
        variant.setPrice(request.getPrice());
        variant.setDiscount(request.getDiscount());
        variant.setStatus(request.getStatus());
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ResourceName.PRODUCT, FieldName.ID, request.getProductId()));
            variant.setProduct(product);
        }
        variant.setUpdatedAt(Instant.now());
        return variant;
    }

    private VariantResponse mapToResponse(Variant Variant) {
        VariantResponse response = new VariantResponse();
        response.setId(Variant.getId());
        response.setCreatedAt(Variant.getCreatedAt());
        response.setUpdatedAt(Variant.getUpdatedAt());
        response.setPrice(Variant.getPrice());
        response.setDiscount(Variant.getDiscount());
        response.setStatus(Variant.getStatus());

        VariantResponse.ProductResponse productResponse = new VariantResponse.ProductResponse();
        productResponse.setId(Variant.getProduct().getId());
        productResponse.setName(Variant.getProduct().getName());
        productResponse.setAuthor(Variant.getProduct().getAuthor());
        productResponse.setCreatedAt(Variant.getProduct().getCreatedAt());
        productResponse.setUpdatedAt(Variant.getProduct().getUpdatedAt());

        response.setProduct(productResponse);
        return response;
    }

}
