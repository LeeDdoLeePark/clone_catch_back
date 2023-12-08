package com.example.catch_clone.user.controller;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.dto.UserLoginRequestDto;
import com.example.catch_clone.user.dto.UserRequestDto;
import com.example.catch_clone.user.service.inter.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/users")
public class UserController{
    private final UserService userService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody @Valid UserRequestDto userRequestDto){
        userService.signUp(userRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<StatusResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto, HttpServletResponse response) {
        StatusResponseDto statusResponseDto = userService.login(response, loginRequestDto);
        return ResponseEntity.ok().body(statusResponseDto);
    }

//    @GetMapping("/profile/{usersId}")
//    public UserDto getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        return null;
//    }

//    @PostMapping("/profile/update/{usersId}")
//    public void updateProfile(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        userService.updateProfile(userRequestDto.id());
//    }

}
