package com.bookshop.controller.client;

import com.bookshop.dto.client.ClientWishRequest;
import com.bookshop.dto.client.ClientWishResponse;
import com.bookshop.entity.general.Wish;
import com.bookshop.repository.client.WishRepository;
import com.bookshop.service.client.ClientCartService;
import com.bookshop.service.client.ClientWishService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client-api/wishes")
@AllArgsConstructor
public class ClientWishController {

    private final ClientWishService wishService;

    @GetMapping
    public ResponseEntity<List<ClientWishResponse>> getWishes(@RequestParam Long userId) {
         return ResponseEntity.status(HttpStatus.OK).body(wishService.get(userId));
    }

    @PostMapping
    public ResponseEntity<ClientWishResponse> createWish(@RequestBody ClientWishRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishService.save(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteWishes(@RequestBody List<Long> ids) {
        wishService.delete(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}