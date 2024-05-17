package com.github.supercodinpj1.domain;

import com.github.supercodinpj1.dto.User.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //선언된 클래스를 DB 테이블과 매핑함
@Table(name = "users")  //Entity와 매핑할 테이블을 지정함
public class UserEntity implements Serializable {

    @Id //속성을 기본키로 설정
    @Column(name = "email") //객체 필드를 테이블의 칼럼과 매핑함
    private String email;

    @Column(name = "password")
    private String password;

    public static UserEntity from(UserDto userDto){
        return UserEntity.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto toDTO(){
        return UserDto.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }
}