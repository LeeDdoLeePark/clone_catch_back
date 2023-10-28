package com.example.catch_clone.review.controller;

import static com.example.catch_clone.review.controller.ReviewController.REVIEW_URI_API;

import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.review.service.ReviewServiceImpl;
import com.example.catch_clone.review.service.inter.ReviewService;
import com.example.catch_clone.security.UserDetailsImpl;
import com.example.catch_clone.security.dto.StatusResponseDto;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(REVIEW_URI_API)
public class ReviewController{
  public static final String REVIEW_URI_API = "/ct/review";
  private final ReviewServiceImpl reviewService;

  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal
      UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = reviewService.addReview(userDetails.getUser(),reviewRequestDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }

  @GetMapping("/reviews/{reviewId}")
  public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId){
    ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(reviewResponseDto);
  }

  @GetMapping("/{storeId}/reviews")
  public ResponseEntity<List<ReviewResponseDto>> getStoreReviews(@PathVariable Long storeId){
    List<ReviewResponseDto> reviewResponseDtos = reviewService.getStoreReviews(storeId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(reviewResponseDtos);
  }

  @GetMapping("/{userId}/reviews")
  public ResponseEntity<List<ReviewResponseDto>> getUserReviews(@PathVariable Long userId){
    List<ReviewResponseDto> reviewResponseDtos = reviewService.getUserReviews(userId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(reviewResponseDtos);
  }

  @PutMapping("/{reviewId}")
  public ResponseEntity<StatusResponseDto> updateReview(@PathVariable Long reviewId, @AuthenticationPrincipal
  UserDetailsImpl userDetails, ReviewRequestDto reviewRequestDto){
    StatusResponseDto statusResponseDto = reviewService.updateReview(userDetails.getUser(),reviewId,reviewRequestDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<StatusResponseDto> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = reviewService.deleteReview(userDetails.getUser(),reviewId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }

}
