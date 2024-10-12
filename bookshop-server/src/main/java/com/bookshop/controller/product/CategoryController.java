package com.bookshop.controller.product;


import com.bookshop.constant.AppConstants;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.product.CategoryRequest;
import com.bookshop.dto.product.CategoryResponse;
import com.bookshop.service.product.CategoryService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ListResponse<CategoryResponse>> getAllCategories(
           @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "filter", required = false) @Nullable String filter,
            @RequestParam(name = "search", required = false) @Nullable String search,
            @RequestParam(name = "all", required = false) boolean all
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll(page, size, sort, filter, search, all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(categoryId));
    }

    @PostMapping("")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(categoryId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.delete(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteCategories(@RequestBody List<Long> categoryIds){
        categoryService.delete(categoryIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}