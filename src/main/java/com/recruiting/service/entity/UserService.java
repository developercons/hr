package com.recruiting.service.entity;

import com.recruiting.domain.Authority;
import com.recruiting.domain.PasswordResetToken;
import com.recruiting.domain.User;
import com.recruiting.domain.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Marta Ginosyan
 */

public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    Authority findByRole(String role);

    User save(User user);

    User approveUser(String id);

    VerificationToken getVerificationToken(String token);

    VerificationToken saveVerificationToken(VerificationToken verificationToken);

    PasswordResetToken getPasswordResetToken(String token);

    void savePasswordResetToken(PasswordResetToken passwordResetToken);

    User findById(String id);
}
