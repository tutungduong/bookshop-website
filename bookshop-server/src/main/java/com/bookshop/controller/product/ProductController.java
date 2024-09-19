package com.bookshop.controller.product;

import com.bookshop.constant.AppConstants;
import com.bookshop.dto.product.ProductRequest;
import com.bookshop.dto.product.ProductResponse;
import com.bookshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class ProductController {

        private final ProductService productService;

        @GetMapping("")
        public ResponseEntity<List<ProductResponse>> getAllProducts(){
            return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long productId){
            return ResponseEntity.status(HttpStatus.OK).body(productService.findById(productId));
        }

        @PostMapping("")
        public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request){
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(request));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductRequest request){
            return ResponseEntity.status(HttpStatus.OK).body(productService.save(productId, request));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId){
            productService.delete(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        @DeleteMapping("")
        public ResponseEntity<Void> deleteProducts(@RequestBody List<Long> productIds){
            productService.delete(productIds);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
}