package com.bookshop.entity.product;

import com.bookshop.entity.BaseEntity;
import com.bookshop.entity.general.Wish;
import com.bookshop.entity.promotion.Promotion;
import com.bookshop.entity.review.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "published_year", nullable = false)
    private Integer publishedYear;

    @Column(name = "pages", nullable = false)
    private Integer pages;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    //    @OneToMany(mappedBy = "CategoryRepository", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Variant> variants = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Wish> wishes = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private Set<Promotion> promotions = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();
}
