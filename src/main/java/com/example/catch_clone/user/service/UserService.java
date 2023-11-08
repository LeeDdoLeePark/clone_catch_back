package com.example.catch_clone.user.service;

import com.example.catch_clone.user.dao.UserRepository;
import com.example.catch_clone.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //회원가입
    public void join(String username, String password){
        //validateDuplicateUser(user);
        userRepository.save(username, password);
        //return user.getId();
    }

    private void validateDuplicateUser(User user){
        userRepository.findByUsername(user.getUsername())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }


    //전체 회원 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findOneId(Long userId){
        return userRepository.findById(userId);
    }

    public Optional<User> findOneName(String userName){
        return userRepository.findByUsername(userName);
    }
}
