package com.example.catch_clone.user.service;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.dao.UserRepository;
import com.example.catch_clone.user.dao.UserRepositoryQuery;
import com.example.catch_clone.user.dto.UserDto;
import com.example.catch_clone.user.entity.User;
import com.example.catch_clone.user.service.inter.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    private final UserRepositoryQuery userRepositoryQuery;
    private final PasswordEncoder passwordEncoder; //비밀번호 암호화

    @Override
    public StatusResponseDto signUp(String accountName, String password) {
        //pwd 암호화 한 후 저장
        Optional<User> user = userRepository.findByUsername(accountName);
        if(user.isPresent()){
         System.out.println("이미 존재하는 유저명입니다.");
        }
        else{
            String encodedPwd = passwordEncoder.encode(password);
            User users = new User(accountName, encodedPwd);
            userRepository.save(users);
        }
        return new StatusResponseDto(201,"Created");
    }

    @Override
    public void updateProfile(Long userId) {

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserProfile(Long userId) {
        return null;
    }

}
