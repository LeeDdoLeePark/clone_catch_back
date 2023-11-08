package com.example.catch_clone.user.controller;

import com.example.catch_clone.user.entity.User;
import com.example.catch_clone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController{

    private UserService userService;
    
    //회원가입, 프로필 조회/수정
    
    //회원가입
    @GetMapping("/signUp") //주소창 url 매핑
    public String signUp(String username, String password) {
        userService.join(username, password);
        return "/main";
    }
    //프로필 수정
    @GetMapping("/updateProfile") // ct/users/profile/update/{usersId}
    public String updateProfile() {

        return "";
    }

    //프로필 조회
    @GetMapping("/getProfile") // ct/users/profile/{usersId}
    public String getProfile(){

        return "";
    }
}

