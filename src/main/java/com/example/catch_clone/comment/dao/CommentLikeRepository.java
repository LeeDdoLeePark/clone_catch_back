package com.example.catch_clone.comment.dao;

import com.example.catch_clone.comment.entity.CommentLike;
import com.example.catch_clone.comment.entity.CommentLikeId;
import org.springframework.data.repository.Repository;

public interface CommentLikeRepository extends Repository<CommentLike, CommentLikeId>,CommentLikeRepositoryQuery {
  void save(CommentLike commentLike);

  void deleteByCommentLikeId(CommentLikeId commentLikeId);

}
