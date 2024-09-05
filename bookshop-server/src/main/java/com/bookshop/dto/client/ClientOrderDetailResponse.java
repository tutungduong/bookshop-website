package com.bookshop.dto.client;

import com.bookshop.entity.cashbook.PaymentMethodType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class ClientOrderDetailResponse {
    private Long orderId;
    private Instant orderCreatedAt;
    private String orderCode;
    private Integer orderStatus;
//    private String orderToName;
//    private String orderToPhone;
//    private String orderToAddress;
//    private String orderToWardName;
//    private String orderToDistrictName;
//    private String orderToProvinceName;
    private BigDecimal orderTotalAmount;
    private BigDecimal orderTax;
    private BigDecimal orderTotalPay;
    private PaymentMethodType orderPaymentMethodType;
    private Integer orderPaymentStatus;
    private Set<ClientOrderVariantResponse> orderItems;
}