package com.bookshop.entity.order;


import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.cashbook.PaymentMethodType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "to_name", nullable = false)
    private String toName;

    @Column(name = "to_phone", nullable = false)
    private String toPhone;

    @Column(name = "to_address", nullable = false)
    private String toAddress;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_cancellation_reason_id")
    @JsonBackReference
    private OrderCancellationReason orderCancellationReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "note")
    private String note;

    @Column(name = "total_amount", nullable = false, columnDefinition = "DECIMAL(15,5)")
    private BigDecimal totalAmount;

    @Column(name = "tax", nullable = false, columnDefinition = "DECIMAL(15,5)")
    private BigDecimal tax;

    @Column(name = "total_pay", nullable = false, columnDefinition = "DECIMAL(15,5)")
    private BigDecimal totalPay;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderVariant> orderVariants = new HashSet<>();

    @Column(name = "payment_method_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;

    // 2 trạng thái: (1) Chưa thanh toán, (2) Đã thanh toán
    @Column(name = "payment_status", nullable = false, columnDefinition = "TINYINT")
    private Integer paymentStatus;
}
