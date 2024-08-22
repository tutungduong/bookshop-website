package com.bookshop.service.product;

import com.bookshop.dto.product.CategoryRequest;
import com.bookshop.dto.product.CategoryResponse;
import com.bookshop.entity.product.Category;
import com.bookshop.repository.product.CategoryRepository;
import com.bookshop.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CategoryService implements CrudService<Long, CategoryRequest, CategoryResponse> {

    private CategoryRepository categoryRepository;

   @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public CategoryResponse save(CategoryRequest request) {
        Category category = mapToEntity(request);
        category = categoryRepository.save(category);
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse save(Long id, CategoryRequest request) {
        return categoryRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(categoryRepository::save)
                .map(this::mapToResponse)
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

    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setCreatedAt(category.getCreatedAt());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setStatus(category.getStatus());
        return response;
    }

    private Category mapToEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setStatus(request.getStatus());
        return category;
    }

    private Category partialUpdate(Category category, CategoryRequest request) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setStatus(request.getStatus());
        category.setUpdatedAt(Instant.now());
        return category;
    }
}
