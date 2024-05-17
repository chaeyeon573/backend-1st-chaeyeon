package com.github.supercodinpj1.respository.Jwt;

import com.github.supercodinpj1.dto.Jwt.Token;

public interface TokenRepository {

    Token save(Token token);
    Token findByRefreshToken(String refreshToken);
    Token findByUserEmail(String userEmail);
    Token findByAccessToken(String accessToken);
    void deleteById(Long id);

}