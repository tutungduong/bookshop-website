package com.bookshop.service.product;


import com.bookshop.dto.product.VariantRequest;
import com.bookshop.dto.product.VariantResponse;
import com.bookshop.entity.product.Product;
import com.bookshop.entity.product.Variant;
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
        variant.setProduct(productRepository.findById(request.getProductId()).orElse(null));
        variant.setPrice(request.getPrice());
        variant.setDiscount(request.getDiscount());
        variant.setStatus(request.getStatus());
        return variant;
    }

    private Variant partialUpdate(Variant variant, VariantRequest request) {
        variant.setPrice(request.getPrice());
        variant.setDiscount(request.getDiscount());
        variant.setStatus(request.getStatus());
        variant.setProduct(productRepository.findById(request.getProductId()).orElse(null));
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
        response.setProduct(mapToProductResponse(Variant));
        return response;
    }

    private VariantResponse.ProductResponse mapToProductResponse(Variant variant) {
        VariantResponse.ProductResponse productResponse = new VariantResponse.ProductResponse();
        productResponse.setId(variant.getProduct().getId());
        productResponse.setCreatedAt(variant.getProduct().getCreatedAt());
        productResponse.setUpdatedAt(variant.getProduct().getUpdatedAt());
        productResponse.setName(variant.getProduct().getName());
        productResponse.setAuthor(variant.getProduct().getAuthor());
        return productResponse;
    }
}
