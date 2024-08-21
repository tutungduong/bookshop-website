package com.bookshop.controller.authentication;



import com.bookshop.dto.authentication.*;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.service.authentication.VerificationService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final VerificationService verificationService;
    private final UserRepository userRepository;

    // Login Account
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser (@RequestBody LoginRequest loginRequest) {

        String jwt = "";
        String refreshToken = "";

        return ResponseEntity.ok(new JwtResponse("Login successful", jwt, refreshToken, Instant.now()));
    }
    // Refresh token account
    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){



         String refreshToken = " ";
         String jwt = " ";

        return ResponseEntity.ok(new JwtResponse("Refresh token", jwt, refreshToken, Instant.now()));
    }

    // Register a new user
    @PostMapping("/register")
     public ResponseEntity<RegistrationResponse> registerUser(@RequestBody UserRequest userRequest) {
        Long userId = verificationService.generateTokenVerify(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new RegistrationResponse(userId));
    }

    // Resend verification token
    @GetMapping("/register/{userId}/resend-token")
    public ResponseEntity<ObjectNode> resendRegistrationToken(@PathVariable Long userId){
        verificationService.resendRegistrationToken(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    // Confirm registration
    @PostMapping("/register/confirm")
    public ResponseEntity<ObjectNode> confirmRegistration(@RequestBody RegistrationRequest registration){
        verificationService.confirmRegistration(registration);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }
    // Change email
    @PutMapping("/registration/{userId}/change-email")
    public ResponseEntity<ObjectNode> changeRegistrationEmail(@PathVariable Long userId, @RequestParam String email) {
        verificationService.changeRegistrationEmail(userId, email);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    // Forgot password
    @GetMapping("/forgot-password")
     public ResponseEntity<ObjectNode> forgotPassword(@RequestParam String email){
        verificationService.forgetPassword(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    // Reset password
     @PutMapping("/reset-password")
     public ResponseEntity<ObjectNode> resetPassword(@RequestBody ResetPasswordRequest resetPassword) {
        verificationService.resetPassword(resetPassword);
         return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
     }

     // Get user info
//    @GetMapping("/info")
//    public ResponseEntity<UserResponse> getAdminUserInfo(Authentication authentication){
//
//        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
//    }
}