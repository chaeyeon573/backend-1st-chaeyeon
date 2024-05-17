package com.github.supercodinpj1.dto.User;

import lombok.*;

/*
    로그인 및 회원가입에 사용되는 데이터
*/

@Getter
@Setter
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 생성
@Builder
public class UserDto {

    private String email;
    private String password;
    private final Role role = Role.USER;    //로그인 가능하게 기본으로 설정해둠

}