package com.example.catch_clone.comment.service;

import com.example.catch_clone.comment.dao.CommentLikeRepository;
import com.example.catch_clone.comment.dao.CommentRepository;
import com.example.catch_clone.comment.dto.CommentRequestDto;
import com.example.catch_clone.comment.dto.CommentResponseDto;
import com.example.catch_clone.comment.entity.Comment;
import com.example.catch_clone.comment.entity.CommentLike;
import com.example.catch_clone.comment.entity.CommentLikeId;
import com.example.catch_clone.comment.service.inter.CommentService;
import com.example.catch_clone.review.dao.ReviewRepository;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.dao.UserRepository;
import com.example.catch_clone.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private ReviewRepository reviewRepository;
  private CommentLikeRepository commentLikeRepository;
  @Override
  @Transactional
  public StatusResponseDto addComment(User user, CommentRequestDto commentRequestDto,Long reviewId) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
    Comment comment = Comment.builder()
        .userId(user.getId())
        .commentContent(commentRequestDto.commentContents())
        .review(review)
        .build();
    commentRepository.save(comment);
    return new StatusResponseDto(200,"OK");
  }

  @Override
  @Transactional(readOnly = true)
  public CommentResponseDto getComment(Long commentId) {
    return commentRepository.findComment(commentId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getReviewComments(Long reviewId) {
    return commentRepository.findCommentsByReviewId(reviewId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getUserComments(Long commentId) {
    return commentRepository.findCommentsByReviewId(commentId);
  }

  @Override
  @Transactional
  public StatusResponseDto updateComment(User user, Long commentId,
      CommentRequestDto commentRequestDto) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );

    if(!comment.isWriter(user,comment)){
      return new StatusResponseDto(403,"Forbidden");
    }

    comment.update(commentRequestDto);

    return new StatusResponseDto(200,"OK");
  }

  @Override
  @Transactional
  public StatusResponseDto deleteComment(User user, Long commentId) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );

    if(!comment.isWriter(user,comment)){
      return new StatusResponseDto(403,"Forbidden");
    }

    commentRepository.deleteById(commentId);

    return new StatusResponseDto(204,"NO_CONTENT");
  }

  @Override
  @Transactional
  public StatusResponseDto requestCommentLike(Long userId, Long commentId) {
    CommentLikeId commentLikeId = CommentLikeId.builder()
        .userId(userId)
        .commentId(commentId)
        .build();

    if(commentLikeRepository.existByCommentLikeId(commentLikeId)){
      commentLikeRepository.deleteByCommentLikeId(commentLikeId);
    }

    else{
      User user = userRepository.findById(userId).orElseThrow(
          () -> new IllegalArgumentException("유효하지 않은 Id입니다")
      );
     Comment comment = commentRepository.findById(commentId).orElseThrow(
          () -> new IllegalArgumentException("유효하지 않은 Id입니다")
      );
      commentLikeRepository.save(new CommentLike(user,comment));
    }

    return new StatusResponseDto(200,"OK");
  }

}
