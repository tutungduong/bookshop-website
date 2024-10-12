package com.bookshop.entity.cashbook;

import com.bookshop.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "payment_method")
public class PaymentMethod extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodType code;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;
}