package com.example.catch_clone.comment.dao;

import com.example.catch_clone.comment.entity.CommentLikeId;
import com.example.catch_clone.review.entity.ReviewLikeId;

public interface CommentLikeRepositoryQuery {
  boolean existByCommentLikeId(CommentLikeId commentLikeId);
}
