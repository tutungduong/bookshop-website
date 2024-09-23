package com.bookshop.controller.client;


import com.bookshop.config.payment.VNPAY.VNPAYService;
import com.bookshop.constant.AppConstants;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.client.ClientConfirmedOrderResponse;
import com.bookshop.dto.client.ClientOrderDetailResponse;
import com.bookshop.dto.client.ClientSimpleOrderRequest;
import com.bookshop.dto.client.ClientSimpleOrderResponse;
import com.bookshop.dto.payment.VNPAY.VNPAYResponse;
import com.bookshop.service.order.ClientOrderService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/client-api/orders")
@AllArgsConstructor

public class ClientOderController {

    private final ClientOrderService clientOrderService;
    private final VNPAYService paymentService;

    @GetMapping()
    public ResponseEntity<ListResponse<ClientSimpleOrderResponse>> getWishes (
            Authentication authentication,
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "filter", required = false) @Nullable String filter
    ){
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(clientOrderService.getAllOrders(username, page, size, sort, filter));
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

    @GetMapping(value = "/success")
    public RedirectView paymentSuccessAndCaptureTransaction(HttpServletRequest request) {
        return null;
    }

    @GetMapping(value = "/cancel")
    public RedirectView paymentCancel(HttpServletRequest request) {
        return null;
    }

     @GetMapping("/vn-pay")
      public ResponseEntity<VNPAYResponse> pay(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.createVnPayPayment(request));
    }

    @GetMapping("/vn-pay-callback")
    public ResponseEntity<VNPAYResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return null;
        } else {
            return null;
        }
    }


}
