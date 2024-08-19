package com.bookshop.entity.order;


import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.cashbook.PaymentMethodType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;


    @Column(name = "payment_method_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;

    // 2 trạng thái: (1) Chưa thanh toán, (2) Đã thanh toán
    @Column(name = "payment_status", nullable = false, columnDefinition = "TINYINT")
    private Integer paymentStatus;
}
