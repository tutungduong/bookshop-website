package com.bookshop.controller.client;

import com.bookshop.constant.AppConstants;
import com.bookshop.dto.authentication.UserResponse;
import com.bookshop.dto.client.ClientEmailSettingUserRequest;
import com.bookshop.dto.client.ClientPasswordSettingUserRequest;
import com.bookshop.dto.client.ClientPersonalSettingUserRequest;
import com.bookshop.dto.client.ClientPhoneSettingUserRequest;
import com.bookshop.service.client.ClientUserService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-api/users")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class ClientUserController {

private final ClientUserService clientUserService;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(clientUserService.getUserInfo(username));
    }

    @PostMapping("/personal")
    public ResponseEntity<UserResponse> updatePersonalSetting(Authentication authentication, @RequestBody ClientPersonalSettingUserRequest userRequest) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(clientUserService.updatePersonalSetting(username, userRequest));
    }

    @PostMapping("/phone")
    public ResponseEntity<UserResponse> updatePhoneSetting(Authentication authentication, @RequestBody ClientPhoneSettingUserRequest userRequest) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(clientUserService.updatePhoneSetting(username, userRequest));

    }

    @PostMapping("/email")
    public ResponseEntity<UserResponse> updateEmailSetting(Authentication authentication, @RequestBody ClientEmailSettingUserRequest userRequest) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(clientUserService.updateEmailSetting(username, userRequest));
    }

    @PostMapping("/password")
    public ResponseEntity<ObjectNode> updatePasswordSetting(Authentication authentication, @RequestBody ClientPasswordSettingUserRequest userRequest) throws Exception {
        String username = authentication.getName();
        clientUserService.updatePasswordSetting(username, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

}