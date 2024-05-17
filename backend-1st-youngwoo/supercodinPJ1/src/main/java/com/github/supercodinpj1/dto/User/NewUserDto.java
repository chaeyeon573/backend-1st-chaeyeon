package com.github.supercodinpj1.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewUserDto {   //유효성 검사 추가

    @NotBlank(message = "이메일을 입력해주세요.") //whitespace, null 불가
    @Email(message = "이메일 주소 형식에 맞게 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z]).{8,}",
            message = "비밀번호는 영문과 숫자만 가능하며, 8자리 이상이어야 합니다.")
    private final String password;

}