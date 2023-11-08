package com.example.catch_clone.user.dao;

import java.util.List;
import java.util.Optional;
import com.example.catch_clone.user.entity.User;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User,Long> {
  User save(String username, String password);
  Optional<User> findById(Long id);
  Optional<User> findByUsername(String username);
  List<User> findAll();
}