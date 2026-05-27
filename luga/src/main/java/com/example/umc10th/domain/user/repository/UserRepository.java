package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 유저 존재 여부 검사
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
