package com.example.catch_clone.user.dao;

import java.util.Optional;
import com.example.catch_clone.user.entity.User;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User,Long> {
  Optional<User> findByUsername(String username);
}
