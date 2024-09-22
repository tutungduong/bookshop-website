package com.bookshop.service.product;

import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.product.CategoryRequest;
import com.bookshop.dto.product.CategoryResponse;
import com.bookshop.entity.product.Category;
import com.bookshop.repository.product.CategoryRepository;
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
public class CategoryService implements CrudService<Long, CategoryRequest, CategoryResponse> {

    private final CategoryRepository categoryRepository;

     @Override
     public ListResponse<CategoryResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Category> sortable = RSQLJPASupport.toSort(sort);
        Specification<Category> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Category> searchable = SearchUtils.parse(search, SearchFields.CATEGORY);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<Category> entities = categoryRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<CategoryResponse> entityResponse = entities.getContent().stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
    }


    @Override
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public CategoryResponse save(CategoryRequest request) {
        Category category = requestToEntity(request);
        category = categoryRepository.save(category);
        return entityToResponse(category);
    }

    @Override
    public CategoryResponse save(Long id, CategoryRequest request) {
        return categoryRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(categoryRepository::save)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        categoryRepository.deleteAllById(ids);
    }

    private Category requestToEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());
        category.setThumbnail(request.getThumbnail());
        category.setStatus(request.getStatus());
        return category;
    }

    private Category partialUpdate(Category category, CategoryRequest request) {
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());
        category.setThumbnail(request.getThumbnail());
        category.setStatus(request.getStatus());
        category.setUpdatedAt(Instant.now());
        return category;
    }

    private CategoryResponse entityToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setSlug(category.getSlug());
        response.setDescription(category.getDescription());
        response.setStatus(category.getStatus());
        response.setThumbnail(category.getThumbnail());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        return response;
    }
}
