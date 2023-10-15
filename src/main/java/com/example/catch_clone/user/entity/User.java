package com.example.catch_clone.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String accountName;

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





}
