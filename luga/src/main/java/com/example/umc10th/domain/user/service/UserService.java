package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDto;
import com.example.umc10th.domain.user.dto.UserResDto;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResDto.SignupResDto signup(UserReqDto.SignupReqDto request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
        User saved = userRepository.save(user);
        return UserResDto.SignupResDto.builder()
                .userId(saved.getId())
                .createdAt(saved.getCreatedAt())
                .build();
    }
}
