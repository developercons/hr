package com.recruiting.repository;

import com.recruiting.domain.PasswordResetToken;

/**
 * @author Marta Ginosyan
 * Date: 11/14/17
 */
public interface PasswordResetTokenRepository extends BaseRepository<PasswordResetToken> {

    PasswordResetToken findPasswordResetTokenByToken(String token);
}
