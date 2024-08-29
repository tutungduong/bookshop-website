package com.bookshop.controller.client;


import com.bookshop.dto.client.ClientCartRequest;
import com.bookshop.dto.client.ClientCartResponse;
import com.bookshop.dto.client.ClientCartVariantKeyRequest;
import com.bookshop.service.client.ClientCartServiceImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-api/carts")
@AllArgsConstructor
public class ClientCartController {

    private ClientCartServiceImpl clientCartService;

    // Bug don't show result when get cart ResponseEntity but it's working fine

    @GetMapping
    public ResponseEntity<List<ClientCartResponse>> getCart(@RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(clientCartService.get(userId));
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
