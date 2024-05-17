package com.github.supercodinpj1.respository.User;

import com.github.supercodinpj1.domain.UserEntity;
import com.github.supercodinpj1.dto.User.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository //db 연동을 처리하는 DAO 클래스
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserJpaRepository userJpaRepository;

    //회원 저장
    @Override
    public UserDto save(UserDto userDto) {
        return userJpaRepository.save(UserEntity.from(userDto)).toDTO();
    }

    //회원 전체 조회
    @Override
    public List<UserDto> findAll() {
        List<UserEntity> memberEntities = userJpaRepository.findAll();

        return memberEntities.stream()
                .map(UserEntity::toDTO)
                .collect(Collectors.toList());
    }

    //회원 이메일 조회
    @Override
    public UserDto findByEmail(String email) {
        UserEntity userDto = userJpaRepository.findByEmail(email);
        if (userDto == null) {
            return null;
        } else {
            return userDto.toDTO();
        }
    }

}