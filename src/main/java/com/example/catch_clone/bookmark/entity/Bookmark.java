package com.example.catch_clone.bookmark.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Bookmark {
  @Id
  @GeneratedValue
  private Long id;  // 따로 복합키 설정 해야될 것 같음.

  @Column
  private Long userId;

  @Column
  private Long storeId;

  @Column
  private String bookmarkMemo;

}
