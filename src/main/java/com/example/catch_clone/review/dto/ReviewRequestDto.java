package com.example.catch_clone.review.dto;

public record ReviewRequestDto(Long reservationId, String reviewContent, Float tasteRating, Float atmosphereRating, Float serviceRating) {

}
