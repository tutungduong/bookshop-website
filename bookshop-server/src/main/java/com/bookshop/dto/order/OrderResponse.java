package com.bookshop.dto.order;

import com.bookshop.dto.authentication.UserResponse;
import com.bookshop.entity.cashbook.PaymentMethodType;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class OrderResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String code;
    private Integer status;
//    private String toName;
//    private String toPhone;
//    private String toAddress;
//    @Nullable
//    private OrderCancellationReasonResponse orderCancellationReason;
    @Nullable
    private String note;
    private OrderResponse.UserResponse user;
    private Set<OrderVariantResponse> orderVariants;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal totalPay;
    private PaymentMethodType paymentMethodType;
    private Integer paymentStatus;

    @Data
    public static class UserResponse{
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
        private String username;
        private String gmail;
    }
}