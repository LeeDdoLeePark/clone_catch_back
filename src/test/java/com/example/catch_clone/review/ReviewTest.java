package com.example.catch_clone.review;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.user.entity.User;
import com.example.catch_clone.util.enums.UserRoleEnum;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;


public class ReviewTest {
  Review review = Review.builder()
      .userId(1L)
      .reviewContent("리뷰내용")
      .storeId(1L)
      .tasteRating(4F)
      .atmosphereRating(4F)
      .serviceRating(4F)
      .build();
  User user = new User(1L,"유저", UserRoleEnum.CUSTOMER,"비밀번호","닉네임","폰번호", LocalDateTime.now(),"a","a");

  ReviewRequestDto reviewRequestDto = new ReviewRequestDto(1L,1L,"리뷰",4F,4F,4F);

  @Test
  void isWriter(){
    //when
    boolean isTrue = review.isWriter(user,review);
    //then
    assertThat(isTrue).isEqualTo(true);
  }

}
