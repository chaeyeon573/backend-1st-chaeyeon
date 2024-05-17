package com.github.supercodinpj1.service.User;

import com.github.supercodinpj1.controller.UserResponse;
import com.github.supercodinpj1.dto.Jwt.Token;
import com.github.supercodinpj1.dto.User.NewUserDto;
import com.github.supercodinpj1.dto.User.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface  UserService {

    String encodePassword(String password);
    boolean matchesPassword(String rawPassword, String encodedPassword);

    UserDto register(NewUserDto userDto);
    Token login(String email, String pw) throws Exception;
    public String logout(HttpServletRequest request, String email);

    UserResponse getByEmail(String email);

}