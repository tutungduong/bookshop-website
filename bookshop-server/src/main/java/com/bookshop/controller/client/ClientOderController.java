package com.bookshop.controller.client;


import com.bookshop.dto.client.ClientConfirmedOrderResponse;
import com.bookshop.dto.client.ClientOrderDetailResponse;
import com.bookshop.dto.client.ClientSimpleOrderRequest;
import com.bookshop.dto.client.ClientSimpleOrderResponse;
import com.bookshop.service.order.ClientOrderService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-api/orders")
@AllArgsConstructor

public class ClientOderController {

    private final ClientOrderService clientOrderService;

    @GetMapping
    public ResponseEntity<List<ClientSimpleOrderResponse>> getAllOrder(Authentication authentication) {

        String username = authentication.getName();

        return ResponseEntity.status(HttpStatus.OK).body(clientOrderService.get(username));
    }

    @GetMapping("/code")
    public ResponseEntity<ClientOrderDetailResponse> getOrder(@RequestParam String code) {
        return ResponseEntity.status(HttpStatus.OK).body(clientOrderService.getCode(code));
    }

   @PutMapping("/cancel/{code}")
    public ResponseEntity<ObjectNode> cancelOrder(@PathVariable String code) {
        clientOrderService.cancelOrder(code);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    @PostMapping
    public ResponseEntity<ClientConfirmedOrderResponse> createClientOrder(@RequestBody ClientSimpleOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientOrderService.createClientOrder(request));
    }

}
