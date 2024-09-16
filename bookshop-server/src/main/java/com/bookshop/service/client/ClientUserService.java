package com.bookshop.service.client;

import com.bookshop.dto.authentication.UserResponse;
import com.bookshop.dto.client.ClientEmailSettingUserRequest;
import com.bookshop.dto.client.ClientPasswordSettingUserRequest;
import com.bookshop.dto.client.ClientPersonalSettingUserRequest;
import com.bookshop.dto.client.ClientPhoneSettingUserRequest;

public interface ClientUserService {
    UserResponse getUserInfo(String username);
    UserResponse updatePersonalSetting(String username, ClientPersonalSettingUserRequest userRequest);
    UserResponse updateEmailSetting(String username, ClientEmailSettingUserRequest userRequest);
    UserResponse updatePhoneSetting(String username, ClientPhoneSettingUserRequest userRequest);
    void updatePasswordSetting(String username, ClientPasswordSettingUserRequest userRequest);
}
