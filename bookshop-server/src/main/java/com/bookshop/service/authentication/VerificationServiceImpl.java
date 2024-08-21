package com.bookshop.service.authentication;

import com.bookshop.dto.authentication.UserRequest;
import com.bookshop.repository.authentication.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final UserRepository userRepository;

    @Override
    public Long generateTokenVerify(UserRequest userRequest) {
        return null;
    }

    @Override
    public void resendRegistrationToken(Long userId) {

    }

    @Override
    public void changeRegistrationEmail(Long userId, String emailUpdate) {

    }

    @Override
    public void forgetPassword(String email) {

    }


}
