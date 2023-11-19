package com.example.catch_clone.user.controller;

import com.example.catch_clone.security.UserDetailsImpl;
import com.example.catch_clone.user.dto.UserDto;
import com.example.catch_clone.user.dto.UserRequestDto;
import com.example.catch_clone.user.service.inter.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/users")
public class UserController{
    private final UserService userService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody UserRequestDto userRequestDto){
        userService.signUp(userRequestDto.accountName(), userRequestDto.password());
    }

    @GetMapping("/profile/{usersId}")
    public UserDto getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/profile/update/{usersId}")
    public void updateProfile(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        userService.updateProfile(userRequestDto.id());
    }

}
