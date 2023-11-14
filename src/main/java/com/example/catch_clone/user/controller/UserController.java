package com.example.catch_clone.user.controller;

import com.example.catch_clone.security.UserDetailsImpl;
import com.example.catch_clone.user.dto.UserDto;
import com.example.catch_clone.user.service.inter.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/user")
public class UserController{
    private final UserService userService;

    @PostMapping("/signUp")
    public void signUp(@RequestParam String accountName, @RequestParam String password){
        //account_name 중복성 체크(id)
        //ERD에 있는 ID는 스프링에서 자동으로 만들어주는 인덱스
        //pwd 스프링 어노테이션으로 암호화(PasswordEncoder)
        userService.signUp(accountName, password);
    }

    @GetMapping()
    public UserDto getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getUserProfile(userDetails.getUserId());
    }

    @PostMapping
    public void updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        userService.updateProfile(userDetails.getUserId());
    }

}
