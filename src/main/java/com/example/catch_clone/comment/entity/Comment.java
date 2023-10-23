package com.example.catch_clone.comment.entity;

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
public class Comment {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long userId;

  @Column
  private Long reviewId;

  @Column
  private String commentContent;

  @Column
  private LocalDateTime createdAt;

  @Column
  private Integer likeCount;



}
