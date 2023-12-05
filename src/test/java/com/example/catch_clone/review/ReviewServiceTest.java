package com.example.catch_clone.review;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.catch_clone.review.dao.ReviewLikeRepository;
import com.example.catch_clone.review.dao.ReviewRepository;
import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.review.entity.ReviewLike;
import com.example.catch_clone.review.entity.ReviewLikeId;
import com.example.catch_clone.review.service.ReviewServiceImpl;
import com.example.catch_clone.user.dao.UserRepository;
import com.example.catch_clone.user.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

  @Mock
  ReviewRepository reviewRepository;

  @Mock
  ReviewLikeRepository reviewLikeRepository;

  @Mock
  UserRepository userRepository;

  @InjectMocks
  ReviewServiceImpl reviewService;

  Long userId = 1L;
  Long storeId = 1L;
  Long reservationId = 1L;
  Long reviewId = 1L;

  ReviewRequestDto reviewRequestDto = new ReviewRequestDto(storeId,reservationId,"리뷰내용",4F,4F,4F);
  ReviewResponseDto reviewResponseDto = new ReviewResponseDto(1L,"리뷰내용",4F,4F,4F,4F, LocalDateTime.now(),1L);
  User user = mock(User.class);
  Review review = Review.builder()
      .userId(userId)
      .reviewContent(reviewRequestDto.reviewContent())
      .storeId(reviewRequestDto.storeId())
      .tasteRating(reviewRequestDto.tasteRating())
      .atmosphereRating(reviewRequestDto.atmosphereRating())
      .serviceRating(reviewRequestDto.serviceRating())
      .build();


  @Test
  void addReview(){
    //given
//    doNothing().when(reviewRepository).save(review);
    when(user.getId()).thenReturn(userId);
    when(reviewRepository.existReviewByUserIdAndStoreId(userId,storeId)).thenReturn(false);
    //when
    var statusResponseDto = reviewService.addReview(user,reviewRequestDto);

    //then
    assertThat(statusResponseDto.StatusCode()).isEqualTo(201);
    assertThat(statusResponseDto.message()).isEqualTo("Created");
  }


  @Test
  void getReview(){
    //given
    given(reviewRepository.findReview(reviewId)).willReturn(Optional.of(reviewResponseDto));
    //when
    var reviewResponseDto = reviewService.getReview(reviewId);

    //then
    assertThat(reviewResponseDto.reviewContent()).isEqualTo(review.getReviewContent());
    assertThat(reviewResponseDto.tasteRating()).isEqualTo(review.getTasteRating());
    assertThat(reviewResponseDto.atmosphereRating()).isEqualTo(review.getAtmosphereRating());
    assertThat(reviewResponseDto.serviceRating()).isEqualTo(review.getServiceRating());
  }

  @Test
  void getStoreReviews(){
    //given
    List<ReviewResponseDto> reviews = new ArrayList<>();
    reviews.add(reviewResponseDto);
    when(reviewRepository.findAllReviewByStoreId(storeId)).thenReturn(reviews);

    //when
    List<ReviewResponseDto> dtoList = reviewService.getStoreReviews(storeId);

    //then
    assertThat(dtoList.get(0).reviewContent()).isEqualTo(review.getReviewContent());
    assertThat(dtoList.get(0).tasteRating()).isEqualTo(review.getTasteRating());
    assertThat(dtoList.get(0).atmosphereRating()).isEqualTo(review.getAtmosphereRating());
    assertThat(dtoList.get(0).serviceRating()).isEqualTo(review.getServiceRating());
  }

  @Test
  void getUserReviews(){
    //given
    List<ReviewResponseDto> reviews = new ArrayList<>();
    reviews.add(reviewResponseDto);
    when(reviewRepository.findAllReviewByUserId(userId)).thenReturn(reviews);

    //when
    List<ReviewResponseDto> dtoList = reviewService.getUserReviews(userId);

    //then
    assertThat(dtoList.get(0).reviewContent()).isEqualTo(review.getReviewContent());
    assertThat(dtoList.get(0).tasteRating()).isEqualTo(review.getTasteRating());
    assertThat(dtoList.get(0).atmosphereRating()).isEqualTo(review.getAtmosphereRating());
    assertThat(dtoList.get(0).serviceRating()).isEqualTo(review.getServiceRating());
  }

  @Test
  void updateReview(){
    //given
    ReviewRequestDto reviewUpdateRequestDto = new ReviewRequestDto(storeId,reservationId,"수정리뷰내용",3F,4F,4F);
    Review mockingReview = mock(Review.class);
    given(reviewRepository.findById(userId)).willReturn(Optional.of(mockingReview));
    when(mockingReview.isWriter(user,mockingReview)).thenReturn(true);
    doNothing().when(mockingReview).update(reviewUpdateRequestDto);

    //when
    var statusResponseDto = reviewService.updateReview(user,reviewId,reviewUpdateRequestDto);

    //then
    verify(mockingReview,times(1)).isWriter(user,mockingReview);
    verify(mockingReview,times(1)).update(reviewUpdateRequestDto);
    assertThat(statusResponseDto.StatusCode()).isEqualTo(200);
    assertThat(statusResponseDto.message()).isEqualTo("OK");
  }

  @Test
  void deleteReview(){
    //given
    Review mockingReview = mock(Review.class);
    given(reviewRepository.findById(userId)).willReturn(Optional.of(mockingReview));
    when(mockingReview.isWriter(user,mockingReview)).thenReturn(true);

    //when
    var statusResponseDto = reviewService.deleteReview(user,reviewId);

    //then
    verify(mockingReview,times(1)).isWriter(user,mockingReview);
    verify(reviewRepository,times(1)).deleteById(userId);
    assertThat(statusResponseDto.StatusCode()).isEqualTo(204);
    assertThat(statusResponseDto.message()).isEqualTo("NO_CONTENT");
  }

  @Test
  @DisplayName("좋아요 추가")
  void addReviewLike(){
    //given
    ReviewLikeId reviewLikeId = ReviewLikeId.builder()
        .userId(userId)
        .reviewId(reviewId)
        .build();

    when(reviewLikeRepository.existByReviewLikeId(reviewLikeId)).thenReturn(false);
    given(userRepository.findById(userId)).willReturn(Optional.of(mock(User.class)));
    given(reviewRepository.findById(reviewId)).willReturn(Optional.of(mock(Review.class)));

    //when
    var statusResponseDto = reviewService.requestReviewLike(userId,reviewId);

    //then
    verify(reviewLikeRepository,times(1)).save(any());
    assertThat(statusResponseDto.StatusCode()).isEqualTo(200);
    assertThat(statusResponseDto.message()).isEqualTo("OK");
  }

  @Test
  @DisplayName("좋아요 취소")
  void deleteReviewLike(){
    //given
    ReviewLikeId reviewLikeId = ReviewLikeId.builder()
        .userId(userId)
        .reviewId(reviewId)
        .build();

    when(reviewLikeRepository.existByReviewLikeId(reviewLikeId)).thenReturn(true);

    //when
    var statusResponseDto = reviewService.requestReviewLike(userId,reviewId);

    //then
    verify(reviewLikeRepository,times(1)).deleteByReviewLikeId(reviewLikeId);
    assertThat(statusResponseDto.StatusCode()).isEqualTo(200);
    assertThat(statusResponseDto.message()).isEqualTo("OK");
  }
}
