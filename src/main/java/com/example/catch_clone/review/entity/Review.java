package com.example.catch_clone.review.entity;

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
public class Review {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long userId;  //회원ID

  @Column
  private Long storeId; //가맹정ID

  @Column
  private Long reservationId; //예약ID

  @Column
  private String reviewContent; //리뷰내용

  @Column
  private LocalDateTime createdAt;  //생성일자

  @Column
  private Float tasteRating;  //맛별점

  @Column
  private Float atmosphereRating; //분위기별점

  @Column
  private Float serviceRating;  //서비스별점

  @Column
  private Float totalRating;  //모든별점평균

  @Column
  private LocalDateTime modifiedAt; //수정일자

  @Column
  private Integer likeCount;  //좋아요 갯수



}
