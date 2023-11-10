package com.example.catch_clone.review.dao;

import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.review.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryQuery {
  boolean existReviewByUserIdAndStoreId(Long userId,Long storeId);
  Optional<ReviewResponseDto> findReview(Long reviewId);
  List<ReviewResponseDto> findAllReviewByStoreId(Long storeId);
  List<ReviewResponseDto> findAllReviewByUserId(Long userId);
}
