package com.bookshop.service.product;


import com.bookshop.dto.product.CategoryResponse;
import com.bookshop.dto.product.ProductRequest;
import com.bookshop.dto.product.ProductResponse;
import com.bookshop.dto.product.VariantRequest;
import com.bookshop.entity.product.Category;
import com.bookshop.entity.product.Product;
import com.bookshop.entity.product.Variant;
import com.bookshop.repository.product.CategoryRepository;
import com.bookshop.repository.product.ProductRepository;
import com.bookshop.repository.product.VariantRepository;
import com.bookshop.service.CrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements CrudService<Long, ProductRequest, ProductResponse> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        Product product = mapToEntity(request);
        product = productRepository.save(product);
        return mapToResponse(product);
    }

    @Override
    public ProductResponse save(Long id, ProductRequest request)
    {
        return productRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(productRepository::save)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        productRepository.deleteAllById(ids);
    }

     private Product mapToEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setAuthor(request.getAuthor());
        product.setPublisher(request.getPublisher());
        product.setPublishedYear(request.getPublishedYear());
        product.setPages(request.getPages());
        product.setStatus(request.getStatus());
        // Update category only if categoryId from request is not null
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category with ID " + request.getCategoryId() + " not found"));
            product.setCategory(category);
        }
        product.setVariants(request.getVariants().stream()
                .map(variantRequest -> {
                    Variant variant = new Variant();
                      variant.setProduct(product);
                      variant.setPrice(variantRequest.getPrice());
                      variant.setDiscount(variantRequest.getDiscount());
                      variant.setStatus(variantRequest.getStatus());
                      return variant;
                })
         .collect(Collectors.toList()));
        return product;
    }


    private Product partialUpdate(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setAuthor(request.getAuthor());
        product.setPublisher(request.getPublisher());
        product.setPublishedYear(request.getPublishedYear());
        product.setPages(request.getPages());
        product.setStatus(request.getStatus());
        // Update category only if categoryId from request is not null
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category with ID " + request.getCategoryId() + " not found"));
            product.setCategory(category);
        }
        product.setVariants(request.getVariants().stream()
                .map(variantRequest -> {
                    Variant variant = new Variant();
                    variant.setProduct(product);
                    variant.setPrice(variantRequest.getPrice());
                    variant.setDiscount(variantRequest.getDiscount());
                    variant.setStatus(variantRequest.getStatus());
                    return variant;
                })
                .collect(Collectors.toList()));
        product.setUpdatedAt(Instant.now());
        return product;
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setAuthor(product.getAuthor());
        response.setPublisher(product.getPublisher());
        response.setPublishedYear(product.getPublishedYear());
        response.setPages(product.getPages());
        response.setStatus(product.getStatus());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(product.getCategory().getId());
        categoryResponse.setName(product.getCategory().getName());
        categoryResponse.setDescription(product.getCategory().getDescription());
        categoryResponse.setCreatedAt(product.getCategory().getCreatedAt());
        categoryResponse.setUpdatedAt(product.getCategory().getUpdatedAt());
        categoryResponse.setStatus(product.getCategory().getStatus());
        response.setCategory(categoryResponse);

        response.setVariants(product.getVariants().stream()
                .map(variant -> {
                    ProductResponse.VariantResponse variantResponse = new ProductResponse.VariantResponse();
                    variantResponse.setId(variant.getId());
                    variantResponse.setCreatedAt(variant.getCreatedAt());
                    variantResponse.setUpdatedAt(variant.getUpdatedAt());
                    variantResponse.setPrice(variant.getPrice());
                    variantResponse.setDiscount(variant.getDiscount());
                    variantResponse.setStatus(variant.getStatus());
                    return variantResponse;
                })
                .collect(Collectors.toList()));
        return response;
    }
}
