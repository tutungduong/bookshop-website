package com.bookshop.dto.order;

import com.bookshop.entity.cashbook.PaymentMethodType;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class OrderRequest {
    private Integer status;
//    private String toName;
//    private String toPhone;
//    private String toAddress;
    @Nullable
    private Long orderCancellationReasonId;
    @Nullable
    private String note;
    private Long userId;
    private Set<OrderVariantRequest> orderVariants;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal totalPay;
    private PaymentMethodType paymentMethodType;
    private Integer paymentStatus;
}