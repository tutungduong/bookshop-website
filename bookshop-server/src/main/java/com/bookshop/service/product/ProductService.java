package com.bookshop.service.product;


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
    private final VariantRepository variantRepository;

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
        product.setCategory(categoryRepository.findById(request.getCategoryId()).orElse(null));
        product.setVariants(request.getVariants().stream()
            .map(variantRequest -> mapToVariant(variantRequest, product))
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
        product.setCategory(
                            categoryRepository.findById(request.getCategoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found")));
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
        response.setCategory(mapToCategoryResponse(product.getCategory()));
        response.setVariants(product.getVariants().stream()
                .map(this::mapToVariantResponse)
                .collect(Collectors.toList()));
        return response;
    }

    private ProductResponse.CategoryResponse mapToCategoryResponse(Category category){
    if (category == null) {
        return null;
    }
    ProductResponse.CategoryResponse response = new ProductResponse.CategoryResponse();
    response.setId(category.getId());
    response.setCreatedAt(category.getCreatedAt());
    response.setUpdatedAt(category.getUpdatedAt());
    response.setName(category.getName());
    response.setDescription(category.getDescription());
    response.setStatus(category.getStatus());
    return response;
    }

    private Variant mapToVariant(VariantRequest request,Product product) {
        Variant variant = new Variant();
        variant.setProduct(product);
        variant.setPrice(request.getPrice());
        variant.setDiscount(request.getDiscount());
        variant.setStatus(request.getStatus());
        return variant;
    }
    private ProductResponse.VariantResponse mapToVariantResponse(Variant variant) {
        ProductResponse.VariantResponse response = new ProductResponse.VariantResponse();
        response.setId(variant.getId());
        response.setCreatedAt(variant.getCreatedAt());
        response.setUpdatedAt(variant.getUpdatedAt());
        response.setPrice(variant.getPrice());
        response.setDiscount(variant.getDiscount());
        response.setStatus(variant.getStatus());
        return response;
    }


}
