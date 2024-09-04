package com.bookshop.dto.client;

import com.bookshop.entity.cashbook.PaymentMethodType;
import lombok.Data;

@Data
public class ClientSimpleOrderRequest {
    private PaymentMethodType paymentMethodType;
    private Long userId;
}