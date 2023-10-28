package com.example.catch_clone.review.entity;

import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.user.entity.User;
import com.example.catch_clone.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Review extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id", nullable = false)
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

  //좋아요 개수는 임시로 넣어놓음 수정 필요
@Builder
public Review(Long id, Long userId, Long storeId, String reviewContent, Float tasteRating, Float atmosphereRating, Float serviceRating){
  this.id = id;
  this.userId = userId;
  this.storeId = storeId;
  this.reviewContent = reviewContent;
  this.tasteRating = tasteRating;
  this.atmosphereRating = atmosphereRating;
  this.serviceRating = serviceRating;
  this.totalRating = (tasteRating + atmosphereRating + serviceRating) / 3;
  this.likeCount = 0;
}


  public boolean isWriter(User user,Review review){
    return Objects.equals(user.getId(), review.getUserId());
  }

  public void update(ReviewRequestDto reviewRequestDto) {
    this.reviewContent = reviewRequestDto.getReviewContent();
    this.tasteRating = reviewRequestDto.getTasteRating();
    this.atmosphereRating = reviewRequestDto.getAtmosphereRating();
    this.serviceRating = reviewRequestDto.getServiceRating();
    this.totalRating = (reviewRequestDto.getTasteRating() + reviewRequestDto.getAtmosphereRating() + reviewRequestDto.getServiceRating()) / 3;
  }

}
