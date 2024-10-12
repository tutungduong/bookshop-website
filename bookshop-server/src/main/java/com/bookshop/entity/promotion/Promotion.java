package com.bookshop.entity.promotion;

import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "promotion")
public class Promotion extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "percent", nullable = false)
    private Integer percent;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "promotion_product",
            joinColumns = @JoinColumn(name = "promotion_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_id", nullable = false)
    )
    private Set<Product> products = new HashSet<>();
}