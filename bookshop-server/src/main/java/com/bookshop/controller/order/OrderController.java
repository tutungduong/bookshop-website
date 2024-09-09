package com.bookshop.controller.order;

import com.bookshop.dto.order.OrderRequest;
import com.bookshop.dto.order.OrderResponse;
import com.bookshop.service.order.ClientOrderService;
import com.bookshop.service.order.OrderService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final ClientOrderService clientOrderService;
    private final OrderService orderService;


    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long orderId){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(orderId));
    }

    @PostMapping("")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable("id") Long orderId, @RequestBody OrderRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.save(orderId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long orderId){
        orderService.delete(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteOrders(@RequestBody List<Long> orderIds){
        orderService.delete(orderIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/cancel/{code}")
    public ResponseEntity<ObjectNode> cancelOrder(@PathVariable String code) {
        clientOrderService.cancelOrder(code);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

}