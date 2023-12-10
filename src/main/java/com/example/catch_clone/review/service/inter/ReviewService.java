package com.example.catch_clone.review.service.inter;

import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.entity.User;
import java.util.List;

public interface ReviewService {
  //리뷰 생성
  StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId);
  //단일 리뷰 불러오기
  ReviewResponseDto getReview(Long reviewId);
  //가게 리뷰 리스트 불러오기
  List<ReviewResponseDto> getStoreReviews(Long storeId);
  //유저의 리뷰 리스트 가져오기
  List<ReviewResponseDto> getUserReviews(Long userId);
  //리뷰 수정하기(15일 이내로 작성한것만 수정 가능하므로 수정 필요)
  StatusResponseDto updateReview(User user, Long reviewId, ReviewRequestDto reviewRequestDto);
  //리뷰 삭제하기
  StatusResponseDto deleteReview(User user, Long reviewId);



  //좋아요 기능
  StatusResponseDto requestReviewLike(Long userId, Long reviewId);
}
