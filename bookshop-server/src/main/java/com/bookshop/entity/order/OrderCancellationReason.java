package com.bookshop.entity.order;


import com.bookshop.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "order_cancellation_reason")
public class OrderCancellationReason extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @OneToMany(mappedBy = "orderCancellationReason", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();
}