package com.bookshop.entity.authentication;

import com.bookshop.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "refresh_token")
public class RefreshToken extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;
}