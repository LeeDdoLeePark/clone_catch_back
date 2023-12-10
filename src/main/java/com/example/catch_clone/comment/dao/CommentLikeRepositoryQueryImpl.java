package com.example.catch_clone.comment.dao;

import static com.example.catch_clone.comment.entity.QCommentLike.commentLike;

import com.example.catch_clone.comment.entity.CommentLikeId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CommentLikeRepositoryQueryImpl implements CommentLikeRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;


  @Override
  @Transactional
  public boolean existByCommentLikeId(CommentLikeId commentLikeId) {
    return jpaQueryFactory.from(commentLike).where(commentLike.commentLikeId.eq(commentLikeId)).select(commentLike.commentLikeId)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }
}
