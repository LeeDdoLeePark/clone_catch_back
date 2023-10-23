package com.example.catch_clone.user.entity;

import com.example.catch_clone.util.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 25, nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

}
