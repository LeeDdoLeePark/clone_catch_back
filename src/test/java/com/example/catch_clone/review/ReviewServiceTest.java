package com.example.catch_clone.review;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.catch_clone.review.dao.ReviewRepository;
import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.review.service.ReviewServiceImpl;
import com.example.catch_clone.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

  @Mock
  ReviewRepository reviewRepository;

  @InjectMocks
  ReviewServiceImpl reviewService;

  Long userId = 1L;
  Long storeId = 1L;
  Long reviewId = 1L;

  ReviewRequestDto reviewRequestDto = new ReviewRequestDto(userId,storeId,"리뷰내용",4F,4F,4F);
  User user = mock(User.class);
  Review review = Review.builder()
      .userId(reviewRequestDto.userId())
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
    //when
    var statusResponseDto = reviewService.addReview(user,reviewRequestDto);

    //then
    assertThat(statusResponseDto.StatusCode()).isEqualTo(201);
    assertThat(statusResponseDto.message()).isEqualTo("Created");
  }


  @Test
  void getReview(){
    //given
    given(reviewRepository.findById(userId)).willReturn(Optional.of(review));
    //when
    var reviewResponseDto = reviewService.getReview(userId);

    //then
    assertThat(reviewResponseDto.reviewContent()).isEqualTo(review.getReviewContent());
    assertThat(reviewResponseDto.tasteRating()).isEqualTo(review.getTasteRating());
    assertThat(reviewResponseDto.atmosphereRating()).isEqualTo(review.getAtmosphereRating());
    assertThat(reviewResponseDto.serviceRating()).isEqualTo(review.getServiceRating());
  }

  @Test
  void getStoreReviews(){
    //given
    List<Review> reviews = new ArrayList<>();
    reviews.add(review);
    when(reviewRepository.findAllByStoreId(storeId)).thenReturn(reviews);

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
    List<Review> reviews = new ArrayList<>();
    reviews.add(review);
    when(reviewRepository.findAllByUserId(userId)).thenReturn(reviews);

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
    ReviewRequestDto reviewUpdateRequestDto = new ReviewRequestDto(userId,storeId,"수정리뷰내용",3F,4F,4F);
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
}
