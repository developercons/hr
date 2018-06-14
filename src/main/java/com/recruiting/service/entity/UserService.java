package com.recruiting.service.entity;

import com.recruiting.entity.Authority;
import com.recruiting.entity.PasswordResetToken;
import com.recruiting.entity.User;
import com.recruiting.entity.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Marta Ginosyan
 */

public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    Authority findByRole(String role);

    User save(User user);

    User approveUser(Long id);

    VerificationToken getVerificationToken(String token);

    VerificationToken saveVerificationToken(VerificationToken verificationToken);

    PasswordResetToken getPasswordResetToken(String token);

    void savePasswordResetToken(PasswordResetToken passwordResetToken);

    User findById(Long id);
}
