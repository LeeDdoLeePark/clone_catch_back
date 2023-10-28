package com.example.catch_clone.review.service;


import com.example.catch_clone.review.dao.ReviewRepository;
import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.review.service.inter.ReviewService;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  //중복 요청에 대한 처리 필요(이미 등록한 리뷰 재등록 X 수정만 되도록)
  @Override
  @Transactional
  public StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto) {

    Review review = Review.builder()
        .userId(reviewRequestDto.userId())
        .reviewContent(reviewRequestDto.reviewContent())
        .storeId(reviewRequestDto.storeId())
        .tasteRating(reviewRequestDto.tasteRating())
        .atmosphereRating(reviewRequestDto.atmosphereRating())
        .serviceRating(reviewRequestDto.serviceRating())
        .build();

    reviewRepository.save(review);

    return new StatusResponseDto(201,"Created");
  }

  @Override
  @Transactional
  public ReviewResponseDto getReview(Long reviewId) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );

    return new ReviewResponseDto(review.getReviewContent(),
        review.getTasteRating(), review.getAtmosphereRating(), review.getServiceRating(),
        review.getTotalRating(), review.getCreatedAt(),review.getLikeCount());
  }

  @Override
  @Transactional
  public List<ReviewResponseDto> getStoreReviews(Long storeId) {
    return reviewRepository.findAllByStoreId(storeId).stream().map(ReviewResponseDto::valueOf).toList();
  }

  @Override
  @Transactional
  public List<ReviewResponseDto> getUserReviews(Long userId) {
    return reviewRepository.findAllByUserId(userId).stream().map(ReviewResponseDto::valueOf).toList();
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

}
