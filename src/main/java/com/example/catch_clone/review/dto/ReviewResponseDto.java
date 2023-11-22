package com.example.catch_clone.review.dto;

import com.example.catch_clone.review.entity.Review;
import java.time.LocalDateTime;

public record ReviewResponseDto(Long reviewId, String reviewContent, Float tasteRating, Float atmosphereRating, Float serviceRating, Float totalRating, LocalDateTime createdAt,Long likeCount) {
//  public static ReviewResponseDto valueOf(Review review,Integer likeCount){
//    return new ReviewResponseDto(review.getReviewContent(),review.getTasteRating(),review.getAtmosphereRating(),review.getServiceRating(),review.getTotalRating(),review.getCreatedAt(),likeCount);
//  }
}
