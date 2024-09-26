package com.bookshop.controller.product;

import com.bookshop.constant.AppConstants;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.product.VariantRequest;
import com.bookshop.dto.product.VariantResponse;
import com.bookshop.service.product.VariantService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variant")
@AllArgsConstructor
public class VariantController {

    private final VariantService variantService;

    @GetMapping("")
    public ResponseEntity<ListResponse<VariantResponse>> getAllVariants(
           @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "filter", required = false) @Nullable String filter,
            @RequestParam(name = "search", required = false) @Nullable String search,
            @RequestParam(name = "all", required = false) boolean all
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(variantService.findAll(page, size, sort, filter, search, all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariantResponse> getVariant(@PathVariable("id") Long variantId){
        return ResponseEntity.status(HttpStatus.OK).body(variantService.findById(variantId));
    }

    @PostMapping("")
    public ResponseEntity<VariantResponse> createVariant(@RequestBody VariantRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(variantService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariantResponse> updateVariant(@PathVariable("id") Long variantId, @RequestBody VariantRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(variantService.save(variantId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable("id") Long variantId){
        variantService.delete(variantId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteVariants(@RequestBody List<Long> variantIds){
        variantService.delete(variantIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}