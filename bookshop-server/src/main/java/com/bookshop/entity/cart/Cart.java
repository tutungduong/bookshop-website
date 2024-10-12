package com.bookshop.entity.cart;

import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.authentication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartVariant> cartVariants = new HashSet<>();

    // 2 trạng thái: (1) Normal, (2) Complete
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;
}
