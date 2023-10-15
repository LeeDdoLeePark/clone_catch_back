package com.example.catch_clone.stores.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class StoreFiles {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String fileUrl; //파일URL

  @Column
  private LocalDateTime createdAt;  //생성일자



}
