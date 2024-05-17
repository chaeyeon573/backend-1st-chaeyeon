package com.github.supercodinpj1.service.Jwt;

import com.github.supercodinpj1.dto.User.UserDto;
import jakarta.servlet.http.HttpServletResponse;

public interface JwtService {

    void createAccessTokenHeader(HttpServletResponse response, String refreshToken);
    UserDto checkAccessTokenValid(String accessToken);

}