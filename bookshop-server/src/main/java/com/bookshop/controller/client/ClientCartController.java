package com.bookshop.controller.client;


import com.bookshop.dto.client.ClientCartRequest;
import com.bookshop.dto.client.ClientCartResponse;
import com.bookshop.dto.client.ClientCartVariantKeyRequest;
import com.bookshop.dto.client.ClientCartVariantResponse;
import com.bookshop.entity.cart.Cart;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.cart.CartRepository;
import com.bookshop.repository.cart.CartVariantRepository;
import com.bookshop.service.client.ClientCartService;
import com.bookshop.service.client.ClientCartServiceImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client-api/carts")
@AllArgsConstructor
public class ClientCartController {

    private ClientCartServiceImpl clientCartService;

    @GetMapping
    public ResponseEntity<ClientCartResponse> getCart(@RequestParam String username){
        return ResponseEntity.status(HttpStatus.OK).body((ClientCartResponse) clientCartService.get(username));
    }

    @PostMapping
    public ResponseEntity<ClientCartResponse> saveCart(@RequestBody ClientCartRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCartService.save(request));
    }

     @DeleteMapping
    public ResponseEntity<ObjectNode> deleteCart(@RequestBody List<ClientCartVariantKeyRequest> idRequests) {
        clientCartService.delete(idRequests);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
     }
}
