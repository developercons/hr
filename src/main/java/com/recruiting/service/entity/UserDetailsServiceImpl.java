package com.recruiting.service.entity;

import com.recruiting.entity.Authority;
import com.recruiting.entity.PasswordResetToken;
import com.recruiting.entity.User;
import com.recruiting.entity.VerificationToken;
import com.recruiting.repository.AuthorityRepository;
import com.recruiting.repository.PasswordResetTokenRepository;
import com.recruiting.repository.UserRepository;
import com.recruiting.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marta Ginosyan
 */

@Service("userService")
//@Transactional
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return user == null ? null : buildUserForAuthentication(user);
    }

    @Override
    public Authority findByRole(String role) {
        return authorityRepository.findByRole(role);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities());
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User approveUser(Long id) {
        User user = userRepository.findOne(id);
        user.setApproved(true);
        Authority authority = authorityRepository.findByRole(user.getDtype().toUpperCase());
        user.setRole(authority);
        return userRepository.save(user);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findVerificationTokenByToken(token);
    }

    @Override
    public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findPasswordResetTokenByToken(token);
    }

    @Override
    public void savePasswordResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }
}
