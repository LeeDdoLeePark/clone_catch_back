package com.example.catch_clone.review.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ReviewImages {

  @Id
  @GeneratedValue
  private Long id;  //여기도 리뷰ID를 fk로 가지고 있고 pk가 따로 없는데 혹시 몰라 남겨놓습니다.


  @Column
  private Long reviewId;  //리뷰ID

  @Column
  private String reviewUrl; //리뷰 사진

}
