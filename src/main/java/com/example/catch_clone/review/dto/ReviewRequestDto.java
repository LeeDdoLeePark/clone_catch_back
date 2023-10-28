package com.example.catch_clone.review.dto;

import lombok.Getter;

@Getter
public record ReviewRequestDto(Long userId, Long storeId, String reviewContent, Float tasteRating, Float atmosphereRating, Float serviceRating) {

}
