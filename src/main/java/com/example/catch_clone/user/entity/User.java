package com.example.catch_clone.user.entity;

import com.example.catch_clone.util.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 25, nullable = false, unique = true)
  private String username;

  @Column
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  @Column
  private String password;

  @Column
  private String nickName;

  @Column
  private String phoneNumber;

  @Column
  private LocalDateTime createdAt;

  @Column
  private String aboutMe;

  @Column
  private String profileUrl;

  public User(String username, String password){
    this.username = username;
    this.password = password;
  }

}
