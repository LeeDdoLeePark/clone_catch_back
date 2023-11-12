package com.example.catch_clone.review.dao;


import com.example.catch_clone.review.entity.ReviewLikeId;

public interface ReviewLikeRepositoryQuery {
  boolean existByReviewLikeId(ReviewLikeId reviewLikeId);
}
