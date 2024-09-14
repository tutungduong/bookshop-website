package com.bookshop.dto.authentication;


import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private String avatar;
    private Integer status;
}
