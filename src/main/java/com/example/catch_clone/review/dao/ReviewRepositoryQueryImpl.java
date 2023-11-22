package com.example.catch_clone.review.dao;

import static com.example.catch_clone.review.entity.QReview.review;
import static com.example.catch_clone.review.entity.QReviewLike.reviewLike;

import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReviewRepositoryQueryImpl implements ReviewRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public boolean existReviewByUserIdAndStoreId(Long userId,Long storeId) {
    return jpaQueryFactory.from(review).where(review.userId.eq(userId),review.storeId.eq(storeId)).select(review.userId,review.storeId)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ReviewResponseDto> findReview(Long reviewId) {
    return Optional.ofNullable(
        jpaQueryFactory
        .select(
            Projections.bean(
                ReviewResponseDto.class
                , review.id
                , review.reviewContent
                , review.totalRating
                , review.atmosphereRating
                , review.serviceRating
                , review.totalRating
                , review.createdAt
                , ExpressionUtils.as
                    (
                    JPAExpressions.select(Wildcard.count)
                        .from(reviewLike)
                        .leftJoin(reviewLike.review)
                        .where(reviewLikeEqByReviewId(reviewId)),
                    "likeCount"))
        )
        .from(review)
        .where(review.id.eq(reviewId))
        .fetchFirst());
  }


  @Override
  @Transactional(readOnly = true)
  public List<ReviewResponseDto> findAllReviewByStoreId(Long storeId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                ReviewResponseDto.class
                , review.id
                , review.reviewContent
                , review.totalRating
                , review.atmosphereRating
                , review.serviceRating
                , review.totalRating
                , review.createdAt
                , ExpressionUtils.as
                    (
                        JPAExpressions.select(Wildcard.count)
                            .from(reviewLike)
                            .leftJoin(reviewLike.review)
                            .where(reviewLikeEqByStoreId(storeId)),
                        "likeCount"))
        )
        .from(review)
        .where(review.id.eq(storeId))
        .fetch();
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReviewResponseDto> findAllReviewByUserId(Long userId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                ReviewResponseDto.class
                , review.id
                , review.reviewContent
                , review.totalRating
                , review.atmosphereRating
                , review.serviceRating
                , review.totalRating
                , review.createdAt
                , ExpressionUtils.as
                    (
                        JPAExpressions.select(Wildcard.count)
                            .from(reviewLike)
                            .leftJoin(reviewLike.review)
                            .where(reviewLikeEqByUserId(userId)),
                        "likeCount"))
        )
        .from(review)
        .where(review.id.eq(userId))
        .fetch();
  }

  private BooleanExpression reviewLikeEqByReviewId(Long reviewId) {
    return Objects.nonNull(reviewId) ? reviewLike.review.id.eq(reviewId) : null;
  }

  private BooleanExpression reviewLikeEqByStoreId(Long storeId) {
    return Objects.nonNull(storeId) ? reviewLike.review.storeId.eq(storeId) : null;
  }

  private BooleanExpression reviewLikeEqByUserId(Long userId) {
    return Objects.nonNull(userId) ? reviewLike.review.userId.eq(userId) : null;
  }

}
