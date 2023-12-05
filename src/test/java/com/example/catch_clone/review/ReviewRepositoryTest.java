package com.example.catch_clone.review;

import com.example.catch_clone.config.TestConfig;
import com.example.catch_clone.review.dao.ReviewRepository;
import com.example.catch_clone.review.entity.Review;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
@Rollback
public class ReviewRepositoryTest {

  @Autowired
  ReviewRepository reviewRepository;
  @Test
  void reviewSaveTest(){
  //given
  var review = Review.builder().reviewContent("리뷰").reservationId(1L).storeId(1L).tasteRating(2F).atmosphereRating(2F).serviceRating(2F).build();
  //when
  reviewRepository.save(review);

  }

//Mysql등 데이터 저장소 필요 h2로는 테스트 불가능하므로 일단 보류
  @Test
  void reviewFindByIdTest(){

  }

}
