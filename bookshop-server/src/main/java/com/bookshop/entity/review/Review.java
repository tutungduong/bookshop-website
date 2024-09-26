package com.bookshop.entity.review;

import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "review", uniqueConstraints = @UniqueConstraint(name = "uc_review", columnNames = {"user_id", "product_id"}))
public class Review extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 5 mức đánh giá: 1, 2, 3, 4, 5
    @Column(name = "rating_score", nullable = false, columnDefinition = "TINYINT")
    private Integer ratingScore;

    // Reference: https://stackoverflow.com/a/31610134
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "reply", columnDefinition = "TEXT")
    private String reply;

    // TODO: 6 trạng thái: Chưa duyệt, Đã duyệt, Không duyệt, Cập nhật chưa duyệt, Cập nhật đã duyệt, Cập nhật không duyệt
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;
}