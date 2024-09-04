package com.bookshop.dto.client;

import com.bookshop.entity.cashbook.PaymentMethodType;
import lombok.Data;

@Data
public class ClientConfirmedOrderResponse {
    private String orderCode;
    private PaymentMethodType orderPaymentMethodType;
}