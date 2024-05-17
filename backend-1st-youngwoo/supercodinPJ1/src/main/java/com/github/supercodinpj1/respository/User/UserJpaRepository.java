package com.github.supercodinpj1.respository.User;

import com.github.supercodinpj1.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Jpa를 이용한 Repository
*/

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    //email을 통해 사용자 정보를 가져옴
    public UserEntity findByEmail(String email);

}