package com.bookshop.controller.product;

import com.bookshop.dto.product.VariantRequest;
import com.bookshop.dto.product.VariantResponse;
import com.bookshop.service.product.VariantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variant")
@AllArgsConstructor
public class VariantController {

    private VariantService variantService;

    @GetMapping("")
    public ResponseEntity<List<VariantResponse>> getAllVariants(){
        return ResponseEntity.status(HttpStatus.OK).body(variantService.findAll());
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