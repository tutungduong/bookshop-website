package com.bookshop.service.authentication;

import com.bookshop.dto.authentication.RegistrationRequest;
import com.bookshop.dto.authentication.ResetPasswordRequest;
import com.bookshop.dto.authentication.UserRequest;

public interface VerificationService {
     Long generateTokenVerify(UserRequest userRequest);

    void resendRegistrationToken(Long userId);

    void confirmRegistration(RegistrationRequest registration);

    void changeRegistrationEmail(Long userId, String emailUpdate);

    void forgetPassword(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);
}
