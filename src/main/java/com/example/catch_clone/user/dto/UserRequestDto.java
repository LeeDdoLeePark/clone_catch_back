package com.example.catch_clone.user.dto;


public record UserRequestDto(Long id, String accountName, String password, String nickname, String phoneNumber, java.time.LocalDateTime createdAt) {

}
