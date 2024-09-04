package com.bookshop.controller.client;


import com.bookshop.dto.client.ClientConfirmedOrderResponse;
import com.bookshop.dto.client.ClientSimpleOrderRequest;
import com.bookshop.service.order.OrderServiceImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/client-api/orders")
@AllArgsConstructor

public class ClientOderController {

    private OrderServiceImpl orderService;

    @GetMapping
    public ResponseEntity<List<ObjectNode>> getAllOrder() {
        return null;
    }

    @GetMapping("/code")
    public ResponseEntity<ObjectNode> getOrder() {
        return null;
    }

    @PutMapping("/cancel/{code}")
    public ResponseEntity<ObjectNode> cancelOrder() {
        return null;
    }

    @PostMapping
    public ResponseEntity<ClientConfirmedOrderResponse> createClientOrder(@RequestBody ClientSimpleOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createClientOrder(request));
    }

    @GetMapping(value = "/success")
    public RedirectView paymentSuccessAndCaptureTransaction() {
        return null;
    }

    @GetMapping(value = "/cancel")
    public RedirectView  paymentCancel() {
        return null;
    }

}
