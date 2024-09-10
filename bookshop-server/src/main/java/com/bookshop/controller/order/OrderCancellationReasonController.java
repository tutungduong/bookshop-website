package com.bookshop.controller.order;


import com.bookshop.dto.order.OrderCancellationReasonRequest;
import com.bookshop.dto.order.OrderCancellationReasonResponse;
import com.bookshop.dto.order.OrderRequest;
import com.bookshop.dto.order.OrderResponse;
import com.bookshop.service.order.OrderCancellationReasonService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-cancellation-reasons")
@AllArgsConstructor
public class OrderCancellationReasonController {
    private final OrderCancellationReasonService orderCancellationReasonService;

    @GetMapping("")
    public ResponseEntity<List<OrderCancellationReasonResponse>> getAllOrderCancellationReasons() {
        return ResponseEntity.status(HttpStatus.OK).body(orderCancellationReasonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderCancellationReasonResponse> getOrderCancellationReason(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(orderCancellationReasonService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<OrderCancellationReasonResponse> createOrderCancellationReason(@RequestBody OrderCancellationReasonRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCancellationReasonService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderCancellationReasonResponse> updateOrderCancellationReason(@PathVariable("id") Long id, @RequestBody OrderCancellationReasonRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(orderCancellationReasonService.save(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderCancellationReason(@PathVariable("id") Long id){
        orderCancellationReasonService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteOrderCancellationReasons(@RequestBody List<Long> ids){
        orderCancellationReasonService.delete(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
