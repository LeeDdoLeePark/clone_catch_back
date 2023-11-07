package com.example.catch_clone.review.dao;

import com.example.catch_clone.review.entity.ReviewLike;
import com.example.catch_clone.review.entity.ReviewLikeId;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReviewLikeRepository extends Repository<ReviewLike,ReviewLikeId>,ReviewLikeRepositoryQuery {
  void save(ReviewLike reviewLike);
  Optional<ReviewLike> findByReviewIdAndUserId(Long reviewId, Long userId);
  void deleteByReviewLikeId(ReviewLikeId reviewLikeId);
}
