package com.example.catch_clone.comment.entity;

import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.review.entity.ReviewLikeId;
import com.example.catch_clone.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommentLike {
  @EmbeddedId
  private CommentLikeId commentLikeId;

  //생성자
  @Builder
  public CommentLike(User user, Comment comment) {
    this.user = user;
    this.comment = comment;
    this.commentLikeId = getCommentLikeId(user, comment);
  }

  private static CommentLikeId getCommentLikeId(User user, Comment comment) {
    CommentLikeId id = new CommentLikeId();
    id.setUserId(user.getId());
    id.setCommentId(comment.getId());
    return id;
  }

  //연관관계
  @ManyToOne
  @MapsId("user_id")
  User user;

  @ManyToOne
  @MapsId("comment_id")
  Comment comment;



}
