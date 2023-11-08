package com.example.catch_clone.user;

import com.example.catch_clone.user.dao.UserRepository;
import com.example.catch_clone.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final UserRepository userRepository;

    public SpringConfig(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository);
    }
}
