package com.bookshop.dto.authentication;

import lombok.Data;

@Data
public class RoleRequest {
    private String code;
    private String name;
    private Integer status;
}