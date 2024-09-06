//package com.bookshop.dto.order;
//
//import com.bookshop.dto.authentication.UserResponse;
//import com.bookshop.entity.cashbook.PaymentMethodType;
//import lombok.Data;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//
//@Data
//public class OrderResponse {
//    private Long id;
//    private Instant createdAt;
//    private Instant updatedAt;
//    private String code;
//    private Integer status;
//    private String toName;
//    private String toPhone;
//    private String toAddress;
//    private String toWardName;
//    private String toDistrictName;
//    private String toProvinceName;
//    private OrderResourceResponse orderResource;
//    @Nullable
//    private OrderCancellationReasonResponse orderCancellationReason;
//    @Nullable
//    private String note;
//    private UserResponse user;
//    private Set<OrderVariantResponse> orderVariants;
//    private BigDecimal totalAmount;
//    private BigDecimal tax;
//    private BigDecimal shippingCost;
//    private BigDecimal totalPay;
//    private PaymentMethodType paymentMethodType;
//    private Integer paymentStatus;
//}