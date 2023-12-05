package com.example.catch_clone.review.dao;

import static com.example.catch_clone.review.entity.QReviewLike.reviewLike;

import com.example.catch_clone.review.entity.ReviewLikeId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReviewLikeRepositoryQueryImpl implements ReviewLikeRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional(readOnly = true)
  public boolean existByReviewLikeId(ReviewLikeId reviewLikeId) {
    return jpaQueryFactory.from(reviewLike).where(reviewLike.reviewLikeId.eq(reviewLikeId)).select(reviewLike.reviewLikeId)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }
}
