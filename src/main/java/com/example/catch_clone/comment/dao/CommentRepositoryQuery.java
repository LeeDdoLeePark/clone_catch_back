package com.example.catch_clone.comment.dao;

import com.example.catch_clone.comment.dto.CommentResponseDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import java.util.List;
import java.util.Optional;

public interface CommentRepositoryQuery {
  Optional<CommentResponseDto> findComment(Long commentId);
  List<CommentResponseDto> findCommentsByUserId(Long userId);
  List<CommentResponseDto> findCommentsByReviewId(Long reviewId);

}
