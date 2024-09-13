package com.bookshop.service.authentication;

import com.bookshop.dto.authentication.RegistrationRequest;
import com.bookshop.dto.authentication.ResetPasswordRequest;
import com.bookshop.dto.authentication.UserRequest;
import com.bookshop.dto.authentication.UserResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.authentication.Verification;
import com.bookshop.entity.authentication.VerificationType;
import com.bookshop.exception.ExpiredTokenException;
import com.bookshop.exception.VerificationException;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.authentication.VerificationRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long generateTokenVerify(UserRequest userRequest) {
        // (1) Check if username exists in database
        if(userRepository.existsUserByUsername(userRequest.getUsername())){
            throw new VerificationException("Username is existing");
        }
       // (2) Check if gmail existing in database
        if (userRepository.existsUserByEmail(userRequest.getGmail())) {
            throw new VerificationException("Email already taken");
        }

        // (3) Create user entity with status 2 (non-verified) and save to database

        User user = requestToEntity(userRequest);
        user.setStatus(2);
        userRepository.save(user);

          // (4) Create new verification entity and set user, token

        Verification verification = new Verification();
        String token = generateVerificationToken();

        verification.setUser(user);
        verification.setToken(token);
        verification.setExpiredAt(Instant.now().plus(5, ChronoUnit.MINUTES));
        verification.setType(VerificationType.REGISTRATION);

        verificationRepository.save(verification);

         return user.getId();
    }

    @Override
    public void resendRegistrationToken(Long userId) {
         Optional<Verification> verifyOpt = verificationRepository.findByUserId(userId);

        if (verifyOpt.isPresent()) {
            Verification verification = verifyOpt.get();

            if (verification.getType().equals(VerificationType.REGISTRATION)) {
                String token = generateVerificationToken();

                verification.setToken(token);
                verification.setExpiredAt(Instant.now().plus(5, ChronoUnit.MINUTES));

                verificationRepository.save(verification);

            }
        }
        else{
             throw new VerificationException("User ID is invalid. Please try again!");
        }
    }

    @Override
    public void confirmRegistration(RegistrationRequest registration) {
         Optional<Verification> verifyOpt = verificationRepository.findByUserId(registration.getUserId());

        if (verifyOpt.isPresent()) {
            Verification verification = verifyOpt.get();

            boolean validVerification = verification.getToken().equals(registration.getToken())
                    && verification.getExpiredAt().isAfter(Instant.now())
                    && verification.getType().equals(VerificationType.REGISTRATION);
            if (validVerification) {
                // (1) Set status code and delete row verification
                User user = verification.getUser();
                user.setStatus(1); // Verified
                userRepository.save(user);
                verificationRepository.delete(verification);

                // (2) Create customer entity
            }
            boolean tokenIsExpired = verification.getToken().equals(registration.getToken())
                    && !verification.getExpiredAt().isAfter(Instant.now())
                    && verification.getType().equals(VerificationType.REGISTRATION);

            if (tokenIsExpired) {
                String token = generateVerificationToken();

                verification.setToken(token);
                verification.setExpiredAt(Instant.now().plus(5, ChronoUnit.MINUTES));

                verificationRepository.save(verification);

                throw new ExpiredTokenException("Token is expired, new token has been sent to your email");
            }

            if (!verification.getToken().equals(registration.getToken())) {
                throw new VerificationException("Invalid token");
            }
        }
            else {
                throw new VerificationException("User does not exist");

            }
    }

    @Override
    public void changeRegistrationEmail(Long userId, String emailUpdate) {
          Optional<Verification> verifyOpt = verificationRepository.findByUserId(userId);

    if (verifyOpt.isPresent()) {
        Verification verification = verifyOpt.get();

        User user = verification.getUser();
        user.setEmail(emailUpdate);
        userRepository.save(user);

        String token = generateVerificationToken();
        verification.setToken(token);
        verification.setExpiredAt(Instant.now().plus(5, ChronoUnit.MINUTES));
        verificationRepository.save(verification);

    } else {
        throw new VerificationException("User does not exist");
    }
    }

    @Override
    public void forgetPassword(String email) {
     User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email doesn't exist"));

        if (user.getStatus() == 1) {
            String token = RandomString.make(10);
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }
        else {
            throw new VerificationException("User is not verified");
        }
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
         User user = userRepository
                .findByEmailAndResetPasswordToken(resetPasswordRequest.getEmail(), resetPasswordRequest.getToken())
                .orElseThrow(() -> new RuntimeException("Email and/or token are invalid"));

        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public UserResponse getUserInfo(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    private String generateVerificationToken(){
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    private User requestToEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getGmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return user;
    }

}
