package com.bookshop.dto.client;

import lombok.Data;

@Data
public class ClientPersonalSettingUserRequest {
    private String username;
    private String fullname;
    private String gender;
    private String address;
}