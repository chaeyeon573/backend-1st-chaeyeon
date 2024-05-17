package com.github.supercodinpj1.controller;

import com.github.supercodinpj1.dto.User.UserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String email;

    @Builder
    public UserResponse(String email) {
        this.email = email;
    }

    public static UserResponse of(UserDto userDto){
        return UserResponse.builder()
                .email(userDto.getEmail())
                .build();
    }

}