package com.example.catch_clone.review;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.catch_clone.annotation.WithCustomMockUser;
import com.example.catch_clone.review.controller.ReviewController;
import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.review.service.ReviewServiceImpl;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = ReviewController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
public class ReviewControllerTest {
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  ReviewServiceImpl reviewService;

  Long userId = 1L;
  Long storeId = 1L;
  Long reviewId = 1L;

  @Test
  @WithCustomMockUser
  void addReview() throws Exception{

    ReviewRequestDto reviewRequestDto = new ReviewRequestDto(userId,storeId,"리뷰내용",5F,4F,3F);

    ResultActions resultActions = mockMvc.perform(post("/ct/reviews/{storeId}",storeId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(reviewRequestDto))
        .with(csrf()))
        .andExpect(status().isOk());

        resultActions.andDo(document("reviewController/addReview",
        requestFields(
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 아이디"),
            fieldWithPath("storeId").type(JsonFieldType.NUMBER).description("가게 아이디"),
            fieldWithPath("reviewContent").type(JsonFieldType.STRING).description("리뷰 내용"),
            fieldWithPath("tasteRating").type(JsonFieldType.NUMBER).description("맛 별점"),
            fieldWithPath("atmosphereRating").type(JsonFieldType.NUMBER).description("분위기 별점"),
            fieldWithPath("serviceRating").type(JsonFieldType.NUMBER).description("서비스 별점")
        )
//        responseFields(
//            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
//            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
//        )
    ));
  }

  @Test
  @WithCustomMockUser
  void getReview() throws Exception{

    ReviewResponseDto reviewResponseDto = new ReviewResponseDto("리뷰내용",4F,4F,4F,4F, LocalDateTime.now(),0);

    given(reviewService.getReview(reviewId)).willReturn(reviewResponseDto);

    ResultActions resultActions = mockMvc.perform(get("/ct/reviews/review/{reviewId}",reviewId)
          .with(csrf()))
        .andExpect(status().isOk());


    resultActions.andDo(document("reviewController/getReview",
        responseFields(
            fieldWithPath("reviewContent").type(JsonFieldType.STRING).description("리뷰 내용"),
            fieldWithPath("tasteRating").type(JsonFieldType.NUMBER).description("맛 별점"),
            fieldWithPath("atmosphereRating").type(JsonFieldType.NUMBER).description("분위기 별점"),
            fieldWithPath("serviceRating").type(JsonFieldType.NUMBER).description("서비스 별점"),
            fieldWithPath("totalRating").type(JsonFieldType.NUMBER).description("총 별점"),
            fieldWithPath("createdAt").type(JsonFieldType.STRING).description("작성 일자"),
            fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요 수")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void getStoreReviews() throws Exception{
    List<ReviewResponseDto> reviews = new ArrayList<>();
    ReviewResponseDto reviewResponseDto = new ReviewResponseDto("리뷰내용",4F,4F,4F,4F, LocalDateTime.now(),0);
    reviews.add(reviewResponseDto);

    given(reviewService.getStoreReviews(storeId)).willReturn(reviews);

    ResultActions resultActions = mockMvc.perform(get("/ct/reviews/store/{storeId}",storeId)
            .with(csrf()))
        .andExpect(status().isOk());


    resultActions.andDo(document("reviewController/getStoreReviews",
        responseFields(
            fieldWithPath("[].reviewContent").type(JsonFieldType.STRING).description("리뷰 내용"),
            fieldWithPath("[].tasteRating").type(JsonFieldType.NUMBER).description("맛 별점"),
            fieldWithPath("[].atmosphereRating").type(JsonFieldType.NUMBER).description("분위기 별점"),
            fieldWithPath("[].serviceRating").type(JsonFieldType.NUMBER).description("서비스 별점"),
            fieldWithPath("[].totalRating").type(JsonFieldType.NUMBER).description("총 별점"),
            fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("작성 일자"),
            fieldWithPath("[].likeCount").type(JsonFieldType.NUMBER).description("좋아요 수")
        )
    ));
  }

  @Test
  @WithCustomMockUser
  void getUserReviews() throws Exception{
    List<ReviewResponseDto> reviews = new ArrayList<>();
    ReviewResponseDto reviewResponseDto = new ReviewResponseDto("리뷰내용",4F,4F,4F,4F, LocalDateTime.now(),0);
    reviews.add(reviewResponseDto);

    given(reviewService.getUserReviews(userId)).willReturn(reviews);

    ResultActions resultActions = mockMvc.perform(get("/ct/reviews/user/{userId}",userId)
            .with(csrf()))
        .andExpect(status().isOk());


    resultActions.andDo(document("reviewController/getUserReviews",
        responseFields(
            fieldWithPath("[].reviewContent").type(JsonFieldType.STRING).description("리뷰 내용"),
            fieldWithPath("[].tasteRating").type(JsonFieldType.NUMBER).description("맛 별점"),
            fieldWithPath("[].atmosphereRating").type(JsonFieldType.NUMBER).description("분위기 별점"),
            fieldWithPath("[].serviceRating").type(JsonFieldType.NUMBER).description("서비스 별점"),
            fieldWithPath("[].totalRating").type(JsonFieldType.NUMBER).description("총 별점"),
            fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("작성 일자"),
            fieldWithPath("[].likeCount").type(JsonFieldType.NUMBER).description("좋아요 수")
        )
    ));
  }
  @Test
  @WithCustomMockUser
  void updateReview() throws Exception{
    User user = mock(User.class);
    ReviewRequestDto reviewRequestDto = new ReviewRequestDto(userId,storeId,"리뷰내용",5F,4F,3F);
    StatusResponseDto statusResponseDto = new StatusResponseDto(200,"OK");
    given(reviewService.updateReview(user,reviewId,reviewRequestDto)).willReturn(statusResponseDto);

    ResultActions resultActions = mockMvc.perform(put("/ct/reviews/{reviewId}",reviewId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(reviewRequestDto))
            .accept(MediaType.APPLICATION_JSON)
            .with(csrf()))
            .andExpect(status().isOk());


    resultActions.andDo(document("reviewController/updateReview",
        requestFields(
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 아이디"),
            fieldWithPath("storeId").type(JsonFieldType.NUMBER).description("가게 아이디"),
            fieldWithPath("reviewContent").type(JsonFieldType.STRING).description("리뷰 내용"),
            fieldWithPath("tasteRating").type(JsonFieldType.NUMBER).description("맛 별점"),
            fieldWithPath("atmosphereRating").type(JsonFieldType.NUMBER).description("분위기 별점"),
            fieldWithPath("serviceRating").type(JsonFieldType.NUMBER).description("서비스 별점")
        )
//        responseFields(
//            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
//            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
//        )
    ));
  }

  @Test
  @WithCustomMockUser
  void deleteReview() throws Exception{
    User user = mock(User.class);
    StatusResponseDto statusResponseDto = new StatusResponseDto(200,"OK");
    given(reviewService.deleteReview(user,reviewId)).willReturn(statusResponseDto);

    ResultActions resultActions = mockMvc.perform(delete("/ct/reviews/{reviewId}",reviewId)
          .with(csrf()))
        .andExpect(status().isOk());


    resultActions.andDo(document("reviewController/updateReview"
//        requestFields(
//            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 아이디"),
//            fieldWithPath("storeId").type(JsonFieldType.NUMBER).description("가게 아이디"),
//            fieldWithPath("reviewContent").type(JsonFieldType.STRING).description("리뷰 내용"),
//            fieldWithPath("tasteRating").type(JsonFieldType.NUMBER).description("맛 별점"),
//            fieldWithPath("atmosphereRating").type(JsonFieldType.NUMBER).description("분위기 별점"),
//            fieldWithPath("serviceRating").type(JsonFieldType.NUMBER).description("서비스 별점")
//        )
//        responseFields(
//            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
//            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
//        )
    ));
  }

  @Test
  @WithCustomMockUser
  void requestReviewLike() throws Exception{

    ResultActions resultActions = mockMvc.perform(post("/ct/reviews/{reviewId}/like",reviewId)
            .contentType(MediaType.APPLICATION_JSON)
            .with(csrf()))
        .andExpect(status().isOk());

    resultActions.andDo(document("reviewController/requestReviewLike"
//        responseFields(
//            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("상태 반환 코드"),
//            fieldWithPath("message").type(JsonFieldType.STRING).description("상태 메시지")
//        )
    ));
  }

}
