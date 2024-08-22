package com.bookshop.controller.product;


import com.bookshop.dto.product.CategoryRequest;
import com.bookshop.dto.product.CategoryResponse;
import com.bookshop.service.product.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor

public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
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