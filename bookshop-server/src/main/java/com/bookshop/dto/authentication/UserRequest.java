package com.bookshop.dto.authentication;


import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String gmail;

}
