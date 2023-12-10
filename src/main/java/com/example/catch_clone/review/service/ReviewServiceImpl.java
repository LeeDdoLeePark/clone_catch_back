package com.example.catch_clone.review.service;

import com.example.catch_clone.review.dao.ReviewLikeRepository;
import com.example.catch_clone.review.dao.ReviewRepository;
import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.review.entity.ReviewLike;
import com.example.catch_clone.review.entity.ReviewLikeId;
import com.example.catch_clone.review.service.inter.ReviewService;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.dao.UserRepository;
import com.example.catch_clone.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final ReviewLikeRepository reviewLikeRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId) {
    if(reviewRepository.existReviewByUserIdAndStoreId(user.getId(), storeId)){
      return new StatusResponseDto(400,"Bad Request");
    }

    Review review = Review.builder()
        .userId(user.getId())
        .reservationId(reviewRequestDto.reservationId())
        .reviewContent(reviewRequestDto.reviewContent())
        .storeId(storeId)
        .tasteRating(reviewRequestDto.tasteRating())
        .atmosphereRating(reviewRequestDto.atmosphereRating())
        .serviceRating(reviewRequestDto.serviceRating())
        .build();

    reviewRepository.save(review);

    return new StatusResponseDto(201,"Created");
  }

  @Override
  @Transactional(readOnly = true)
  public ReviewResponseDto getReview(Long reviewId) {
    return reviewRepository.findReview(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReviewResponseDto> getStoreReviews(Long storeId) {
//    return reviewRepository.findAllByStoreId(storeId).stream().map(ReviewResponseDto::valueOf).toList();
    return reviewRepository.findAllReviewByStoreId(storeId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReviewResponseDto> getUserReviews(Long userId) {
//    return reviewRepository.findAllByUserId(userId).stream().map(ReviewResponseDto::valueOf).toList();
    return reviewRepository.findAllReviewByUserId(userId);
  }

  @Override
  @Transactional
  public StatusResponseDto updateReview(User user, Long reviewId,
      ReviewRequestDto reviewRequestDto) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );

    if(!review.isWriter(user,review)){
      return new StatusResponseDto(403,"Forbidden");
    }

    review.update(reviewRequestDto);

    return new StatusResponseDto(200,"OK");
  }

  @Override
  @Transactional
  public StatusResponseDto deleteReview(User user, Long reviewId) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );

    if(!review.isWriter(user,review)){
      return new StatusResponseDto(403,"Forbidden");
    }

    reviewRepository.deleteById(reviewId);

    return new StatusResponseDto(204,"NO_CONTENT");
  }

  @Override
  @Transactional
  public StatusResponseDto requestReviewLike(Long userId, Long reviewId) {
    ReviewLikeId reviewLikeId = ReviewLikeId.builder()
        .userId(userId)
        .reviewId(reviewId)
        .build();

    if(reviewLikeRepository.existByReviewLikeId(reviewLikeId)){
      reviewLikeRepository.deleteByReviewLikeId(reviewLikeId);
    }

    else{
      User user = userRepository.findById(userId).orElseThrow(
          () -> new IllegalArgumentException("유효하지 않은 Id입니다")
      );
      Review review = reviewRepository.findById(reviewId).orElseThrow(
          () -> new IllegalArgumentException("유효하지 않은 Id입니다")
      );
      reviewLikeRepository.save(new ReviewLike(user,review));
    }

    return new StatusResponseDto(200,"OK");
  }

}
