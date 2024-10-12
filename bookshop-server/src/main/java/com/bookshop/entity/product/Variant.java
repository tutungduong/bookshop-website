package com.bookshop.entity.product;


import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.cart.CartVariant;
import com.bookshop.entity.order.OrderVariant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "variant")
public class Variant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<OrderVariant> orderVariants = new HashSet<>();

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<CartVariant> cartVariants = new HashSet<>();
}