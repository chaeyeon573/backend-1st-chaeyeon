package com.github.supercodinpj1.service.User;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("해당 회원을 찾을 수 없습니다.");
    }

}