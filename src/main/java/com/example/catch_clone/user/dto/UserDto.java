package com.example.catch_clone.user.dto;


public record UserDto(Long id, String accountName, String password, String nickname, String phoneNumber, java.time.LocalDateTime createdAt, String aboutMe, String profileUrl) {

}
