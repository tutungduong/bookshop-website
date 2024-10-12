package com.bookshop.entity.authentication;

import com.bookshop.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    @Column(name = "code", nullable = false, unique = true, length = 35)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}