package com.example.catch_clone.user.service.inter;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.dto.UserDto;
import java.util.Optional;

public interface UserService {
    StatusResponseDto signUp(String accountName, String password); //회원가입
    // 프로필 수정
    void updateProfile(Long userId);
    // 프로필 조회
    Optional<UserDto> getUserProfile(Long userId);
}
