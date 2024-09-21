package com.bookshop.service.client.impl;

import com.bookshop.dto.authentication.UserResponse;
import com.bookshop.dto.client.ClientEmailSettingUserRequest;
import com.bookshop.dto.client.ClientPasswordSettingUserRequest;
import com.bookshop.dto.client.ClientPersonalSettingUserRequest;
import com.bookshop.dto.client.ClientPhoneSettingUserRequest;
import com.bookshop.entity.authentication.User;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.service.client.ClientUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientUserServiceImpl implements ClientUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getUserInfo(String username) {
        return userRepository.findByUsername(username)
                .map(this::entityToResponse)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserResponse updatePersonalSetting(String username, ClientPersonalSettingUserRequest userRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        user.setUsername(userRequest.getUsername());
        user.setFullname(userRequest.getFullname());
        user.setGender(userRequest.getGender());
        user.setAddress(userRequest.getAddress());

        userRepository.save(user);

        return entityToResponse(user);
    }

    @Override
    public UserResponse updateEmailSetting(String username, ClientEmailSettingUserRequest userRequest) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        user.setEmail(userRequest.getEmail());

        userRepository.save(user);

        return entityToResponse(user);
    }

    @Override
    public UserResponse updatePhoneSetting(String username, ClientPhoneSettingUserRequest userRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        user.setPhone(userRequest.getPhone());

        userRepository.save(user);

        return entityToResponse(user);
    }

    @Override
    public void updatePasswordSetting(String username, ClientPasswordSettingUserRequest userRequest) {
           User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        if (passwordEncoder.matches(userRequest.getOldPassword(), user.getPassword())) {
            String encodedNewPassword = passwordEncoder.encode(userRequest.getNewPassword());
            user.setPassword(encodedNewPassword);
            userRepository.save(user);

            // Source: https://stackoverflow.com/a/74827122
            // More: https://codingexplained.com/coding/java/spring-framework/returning-empty-json-object-spring-framework
        } else {
            throw new RuntimeException("Wrong old password");
        }
    }

    private UserResponse entityToResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setFullname(user.getFullname());
        response.setGender(user.getGender());
        response.setAddress(user.getAddress());
        response.setAvatar(user.getAvatar());
        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }
}
