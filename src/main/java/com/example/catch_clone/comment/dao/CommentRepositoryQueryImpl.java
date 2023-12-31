package com.example.catch_clone.comment.dao;

import static com.example.catch_clone.comment.entity.QComment.comment;
import static com.example.catch_clone.comment.entity.QCommentLike.commentLike;
import static com.example.catch_clone.user.entity.QUser.user;

import com.example.catch_clone.comment.dto.CommentResponseDto;
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

@RequiredArgsConstructor
public class CommentRepositoryQueryImpl implements CommentRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Optional<CommentResponseDto> findComment(Long commentId) {
    return Optional.ofNullable(
        jpaQueryFactory
            .select(
                Projections.bean(
                    CommentResponseDto.class
                    ,comment.id
                    ,comment.userId
                    ,comment.commentContent
                    ,user.nickName
                    ,comment.createdAt
                    , ExpressionUtils.as
                        (
                            JPAExpressions.select(Wildcard.count)
                                .from(commentLike)
                                .leftJoin(commentLike.comment)
                                .where(commentLikeEqByCommentId(commentId)),
                            "likeCount"))
            )
            .from(comment)
            .leftJoin(user)
            .where(comment.id.eq(commentId),
               user.id.eq(comment.userId))
            .fetchFirst());
  }

  @Override
  public List<CommentResponseDto> findCommentsByUserId(Long userId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                CommentResponseDto.class
                ,comment.id
                ,comment.userId
                ,comment.commentContent
                ,user.nickName
                ,comment.createdAt
                , ExpressionUtils.as
                    (
                        JPAExpressions.select(Wildcard.count)
                            .from(commentLike)
                            .leftJoin(commentLike.comment)
                            .where(commentLikeEqByUserId(userId)),
                        "likeCount"))
            )
        .from(comment)
        .leftJoin(user)
        .where(comment.userId.eq(userId),
            user.id.eq(comment.userId))
        .fetch();
  }


  @Override
  public List<CommentResponseDto> findCommentsByReviewId(Long reviewId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                CommentResponseDto.class
                ,comment.id
                ,comment.userId
                ,comment.commentContent
                ,user.nickName
                ,comment.createdAt
                , ExpressionUtils.as
                    (
                        JPAExpressions.select(Wildcard.count)
                            .from(commentLike)
                            .leftJoin(commentLike.comment)
                            .where(commentLikeEqByReviewId(reviewId)),
                        "likeCount")
            ))
        .from(comment)
        .leftJoin(user)
        .where(comment.review.id.eq(reviewId),
            user.id.eq(comment.userId))
        .fetch();
  }

  private BooleanExpression commentLikeEqByCommentId(Long commentId) {
    return Objects.nonNull(commentId) ? commentLike.comment.id.eq(commentId) : null;
  }
  private BooleanExpression commentLikeEqByUserId(Long userId) {
    return Objects.nonNull(userId) ? commentLike.user.id.eq(userId) : null;
  }
  private BooleanExpression commentLikeEqByReviewId(Long reviewId) {
    return Objects.nonNull(reviewId) ? commentLike.comment.review.id.eq(reviewId) : null;
  }
}
