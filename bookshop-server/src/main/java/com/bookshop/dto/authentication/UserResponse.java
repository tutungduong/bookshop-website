package com.bookshop.dto.authentication;


import lombok.Data;

import java.time.Instant;

@Data
public class UserResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String username;
    private String password;
    private String gmail;
}
