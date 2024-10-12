package com.bookshop.entity.general;

import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "wish", uniqueConstraints = @UniqueConstraint(name = "uc_wish", columnNames = {"user_id", "product_id"}))
public class Wish extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}