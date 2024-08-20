package com.bookshop.entity.product;


import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.cart.CartVariant;
import com.bookshop.entity.order.OrderVariant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "published_year", nullable = false)
    private Integer publishedYear;

    @Column(name = "pages", nullable = false)
    private Integer pages;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<OrderVariant> orderVariants = new HashSet<>();

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<CartVariant> cartVariants = new HashSet<>();
}