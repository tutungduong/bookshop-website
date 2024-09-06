//package com.bookshop.dto.order;
//
//import com.bookshop.entity.cashbook.PaymentMethodType;
//import lombok.Data;
//
//import java.math.BigDecimal;
//
//@Data
//public class OrderRequest {
//    private String code;
//    private Integer status;
//    private String toName;
//    private String toPhone;
//    private String toAddress;
//    private String toWardName;
//    private String toDistrictName;
//    private String toProvinceName;
//    private Long orderResourceId;
//    @Nullable
//    private Long orderCancellationReasonId;
//    @Nullable
//    private String note;
//    private Long userId;
//    private Set<OrderVariantRequest> orderVariants;
//    private BigDecimal totalAmount;
//    private BigDecimal tax;
//    private BigDecimal shippingCost;
//    private BigDecimal totalPay;
//    private PaymentMethodType paymentMethodType;
//    private Integer paymentStatus;
//}