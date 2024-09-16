package com.bookshop.controller.authentication;


import com.bookshop.config.security.JwtUtils;
import com.bookshop.constant.AppConstants;
import com.bookshop.dto.authentication.*;
import com.bookshop.entity.authentication.RefreshToken;
import com.bookshop.entity.authentication.User;
import com.bookshop.exception.RefreshTokenException;
import com.bookshop.service.authentication.RefreshTokenService;
import com.bookshop.service.authentication.VerificationService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class AuthController {

    private final VerificationService verificationService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser (@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        String jwt = jwtUtils.generateJwtToken(authentication);
        String refreshToken = refreshTokenService.createRefreshToken(authentication).getToken();

        return ResponseEntity.ok(new JwtResponse("Login successful", jwt, refreshToken, Instant.now()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){

         String refreshToken = refreshTokenRequest.getRefreshToken();

          String jwt = refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(User::getUsername)
                .map(jwtUtils::generateTokenFromUsername)
                .orElseThrow(() -> new RefreshTokenException("Refresh token was expired. Please make a new signin request!"));

        return ResponseEntity.ok(new JwtResponse("Refresh token", jwt, refreshToken, Instant.now()));
    }

    @PostMapping("/register")
     public ResponseEntity<RegistrationResponse> registerUser(@RequestBody UserRequest userRequest) {
        Long userId = verificationService.generateTokenVerify(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new RegistrationResponse(userId));
    }

    @GetMapping("/register/{userId}/resend-token")
    public ResponseEntity<ObjectNode> resendRegistrationToken(@PathVariable Long userId){
        verificationService.resendRegistrationToken(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    @PostMapping("/register/confirm")
    public ResponseEntity<ObjectNode> confirmRegistration(@RequestBody RegistrationRequest registration){
        verificationService.confirmRegistration(registration);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    @PutMapping("/registration/{userId}/change-email")
    public ResponseEntity<ObjectNode> changeRegistrationEmail(@PathVariable Long userId, @RequestParam String email) {
        verificationService.changeRegistrationEmail(userId, email);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    @GetMapping("/forgot-password")
     public ResponseEntity<ObjectNode> forgotPassword(@RequestParam String email){
        verificationService.forgetPassword(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

     @PutMapping("/reset-password")
     public ResponseEntity<ObjectNode> resetPassword(@RequestBody ResetPasswordRequest resetPassword) {
         verificationService.resetPassword(resetPassword);
         return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
     }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getAdminUserInfo(Authentication authentication){
         String username = authentication.getName();
         return ResponseEntity.status(HttpStatus.OK).body(verificationService.getUserInfo(username));
    }

}