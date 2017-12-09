package com.recruiting.repository;

import com.recruiting.domain.VerificationToken;

/**
 * @author Marta Ginosyan
 * Date: 11/14/17
 */
public interface VerificationTokenRepository extends BaseRepository<VerificationToken> {

    VerificationToken findVerificationTokenByToken(String token);
}