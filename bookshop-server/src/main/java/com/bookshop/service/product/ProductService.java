package com.bookshop.service.product;


import com.bookshop.constant.FieldName;
import com.bookshop.constant.ResourceName;
import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.general.ImageResponse;
import com.bookshop.dto.product.ProductRequest;
import com.bookshop.dto.product.ProductResponse;
import com.bookshop.entity.general.Image;
import com.bookshop.entity.product.Category;
import com.bookshop.entity.product.Product;
import com.bookshop.entity.product.Variant;
import com.bookshop.exception.ResourceNotFoundException;
import com.bookshop.repository.product.CategoryRepository;
import com.bookshop.repository.product.ProductRepository;
import com.bookshop.service.CrudService;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements CrudService<Long, ProductRequest, ProductResponse> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

     @Override
     public ListResponse<ProductResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Product> sortable = RSQLJPASupport.toSort(sort);
        Specification<Product> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Product> searchable = SearchUtils.parse(search, SearchFields.PRODUCT);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<Product> entities = productRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<ProductResponse> entityResponse = entities.getContent().stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        Product product = requestToEntity(request);
        product = productRepository.save(product);
        return entityToResponse(product);
    }

    @Override
    public ProductResponse save(Long id, ProductRequest request)
    {
        return productRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(productRepository::save)
                .map(this::entityToResponse)
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

     private Product requestToEntity(ProductRequest request) {
        Product product = new Product();

        product.setName(request.getName());
        product.setSlug(request.getSlug());
        product.setShortDescription(request.getShortDescription());
        product.setDescription(request.getDescription());
        product.setAuthor(request.getAuthor());
        product.setPublisher(request.getPublisher());
        product.setPublishedYear(request.getPublishedYear());
        product.setPages(request.getPages());
        product.setStatus(request.getStatus());

        Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CATEGORY, FieldName.ID, request.getCategoryId()));
        product.setCategory(category);

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

        product.setImages(request.getImages().stream()
                .map(imageRequest -> {
                    Image image = new Image();
                    image.setProduct(product);
                    image.setName(imageRequest.getName());
                    image.setPath(imageRequest.getPath());
                    image.setContentType(imageRequest.getContentType());
                    image.setSize(imageRequest.getSize());
                    image.setGroup(imageRequest.getGroup());
                    image.setIsThumbnail(imageRequest.getIsThumbnail());
                    image.setIsEliminated(imageRequest.getIsEliminated());
                    return image;
                })
                .collect(Collectors.toList()));

        return product;
    }


    private Product partialUpdate(Product product, ProductRequest request) {

        product.setName(request.getName());
        product.setSlug(request.getSlug());
        product.setShortDescription(request.getShortDescription());
        product.setDescription(request.getDescription());
        product.setAuthor(request.getAuthor());
        product.setPublisher(request.getPublisher());
        product.setPublishedYear(request.getPublishedYear());
        product.setPages(request.getPages());
        product.setStatus(request.getStatus());

        Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CATEGORY, FieldName.ID, request.getCategoryId()));
        product.setCategory(category);

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

        product.setImages(request.getImages().stream()
                .map(imageRequest -> {
                    Image image = new Image();
                    image.setProduct(product);
                    image.setName(imageRequest.getName());
                    image.setPath(imageRequest.getPath());
                    image.setContentType(imageRequest.getContentType());
                    image.setSize(imageRequest.getSize());
                    image.setGroup(imageRequest.getGroup());
                    image.setIsThumbnail(imageRequest.getIsThumbnail());
                    image.setIsEliminated(imageRequest.getIsEliminated());
                    return image;
                })
                .collect(Collectors.toList()));

        product.setUpdatedAt(Instant.now());

        return product;
    }

    private ProductResponse entityToResponse(Product product) {
        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setSlug(product.getSlug());
        response.setName(product.getName());
        response.setShortDescription(product.getShortDescription());
        response.setDescription(product.getDescription());
        response.setAuthor(product.getAuthor());
        response.setPublisher(product.getPublisher());
        response.setPublishedYear(product.getPublishedYear());
        response.setPages(product.getPages());
        response.setStatus(product.getStatus());

        ProductResponse.CategoryResponse  categoryResponse = new ProductResponse.CategoryResponse();
        categoryResponse.setId(product.getCategory().getId());
        categoryResponse.setName(product.getCategory().getName());
        categoryResponse.setSlug(product.getCategory().getSlug());
        categoryResponse.setDescription(product.getCategory().getDescription());
        categoryResponse.setCreatedAt(product.getCategory().getCreatedAt());
        categoryResponse.setUpdatedAt(product.getCategory().getUpdatedAt());
        categoryResponse.setThumbnail(product.getCategory().getThumbnail());
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

        response.setImages(product.getImages().stream()
                .map(image -> {
                    ImageResponse imageResponse = new ImageResponse();
                    imageResponse.setId(image.getId());
                    imageResponse.setName(image.getName());
                    imageResponse.setPath(image.getPath());
                    imageResponse.setContentType(image.getContentType());
                    imageResponse.setSize(image.getSize());
                    imageResponse.setGroup(image.getGroup());
                    imageResponse.setIsThumbnail(image.getIsThumbnail());
                    imageResponse.setIsEliminated(image.getIsEliminated());
                    imageResponse.setCreatedAt(image.getCreatedAt());
                    imageResponse.setUpdatedAt(image.getUpdatedAt());
                    return imageResponse;
                })
                .collect(Collectors.toList()));

        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }
}
