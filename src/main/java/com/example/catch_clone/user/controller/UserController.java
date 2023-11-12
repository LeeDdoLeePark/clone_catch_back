package com.example.catch_clone.user.controller;

import com.example.catch_clone.user.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/user")
public class UserController{
    private final UserService userService;
}
