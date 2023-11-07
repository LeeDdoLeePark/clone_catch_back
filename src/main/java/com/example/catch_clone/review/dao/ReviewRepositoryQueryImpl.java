package com.example.catch_clone.review.dao;

import static com.example.catch_clone.review.entity.QReview.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReviewRepositoryQueryImpl implements ReviewRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public boolean existReviewId(Long reviewId) {
    return jpaQueryFactory.from(review).where(review.id.eq(reviewId)).select(review.id)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }

}
