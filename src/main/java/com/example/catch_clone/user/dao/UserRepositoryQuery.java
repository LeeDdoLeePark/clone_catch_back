package com.example.catch_clone.user.dao;

import com.example.catch_clone.user.dto.UserDto;
import com.example.catch_clone.user.entity.User;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserRepositoryQuery extends Repository<User,Long>{
  void saveAccount(String username, String password);
  void updateProfile();
}
