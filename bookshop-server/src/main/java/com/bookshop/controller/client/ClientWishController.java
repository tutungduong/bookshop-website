package com.bookshop.controller.client;

import com.bookshop.constant.AppConstants;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.client.ClientWishRequest;
import com.bookshop.dto.client.ClientWishResponse;
import com.bookshop.service.client.ClientWishService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-api/wishes")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class ClientWishController {

    private final ClientWishService wishService;

//    @GetMapping
//    public ResponseEntity<List<ClientWishResponse>> getWishess(Authentication authentication) {
//         String username = authentication.getName();
//         return ResponseEntity.status(HttpStatus.OK).body(wishService.get(username));
//    }
    @GetMapping()
    public ResponseEntity<ListResponse<ClientWishResponse>> getWishes (Authentication authentication,
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "filter", required = false) @Nullable String filter
    ){
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(wishService.getAllWishes(username, page, size, sort, filter));
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