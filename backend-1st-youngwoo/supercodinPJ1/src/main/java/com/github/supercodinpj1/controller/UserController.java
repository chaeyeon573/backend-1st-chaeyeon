package com.github.supercodinpj1.controller;

import com.github.supercodinpj1.dto.Jwt.ResponseToken;
import com.github.supercodinpj1.dto.Jwt.Token;
import com.github.supercodinpj1.dto.User.NewUserDto;
import com.github.supercodinpj1.dto.User.UserDto;
import com.github.supercodinpj1.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api") //내부에 선언한 메서드의 URL 리소스 앞에 @RequestMapping의 값이 공통 값으로 추가됨.
@RequiredArgsConstructor
@RestController //사용자 요청을 제어하는 controller 클래스
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> join(@Validated @RequestBody NewUserDto userDto, BindingResult result) {


        try{
            if (result.hasErrors()) {
                log.info("BindingResult error : " + result.hasErrors());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 또는 비밀번호를 형식에 맞게 입력해주세요.");
            }

            if (!isValidEmail(userDto.getEmail())) {
                result.rejectValue("email", "email.invalid", "이메일 형식에 맞게 입력하세요.");
                log.info("이메일 형식에 맞게 입력하세요.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getClass().getSimpleName());
            }

            if (!isValidPassword(userDto.getPassword())) {
                result.rejectValue("password", "password.invalid", "비밀번호 형식에 맞게 입력해주세요.");
                log.info("비밀번호 형식에 맞게 입력해주세요.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getClass().getSimpleName());
            }

            UserDto saveUser = userService.register(userDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.of(saveUser));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto login) {
        try {
            String email = login.getEmail();
            String password = login.getPassword();
            Token token = userService.login(email, password);

            return ResponseEntity.ok().body(ResponseToken.of(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, @RequestBody UserDto userDto) {
        String res = userService.logout(request, userDto.getEmail());
        return ResponseEntity.ok().body(res);
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "(?=.*[0-9])(?=.*[a-z]).{8,}";
        return password.matches(passwordRegex);
    }

}