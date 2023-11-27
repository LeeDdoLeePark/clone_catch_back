package com.example.catch_clone.user.dto;


import com.example.catch_clone.util.customAnnotations.validateNickname.ValidateNickname;
import com.example.catch_clone.util.customAnnotations.validatePassword.ValidatePassword;
import com.example.catch_clone.util.customAnnotations.validateUsername.ValidateUsername;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(@ValidateUsername String username, @ValidatePassword String password, @ValidateNickname String nickName, @NotEmpty String phoneNumber, String aboutMe, String profileUrl) {

}
