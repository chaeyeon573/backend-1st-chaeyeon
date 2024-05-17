package com.github.supercodinpj1.respository.User;

import com.github.supercodinpj1.dto.User.UserDto;

import java.util.List;

public interface UserRepository {

    UserDto save(UserDto userDto);
    public List<UserDto> findAll();
    UserDto findByEmail(String email);

}