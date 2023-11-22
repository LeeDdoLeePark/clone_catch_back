package com.example.catch_clone.comment.service.inter;

import com.example.catch_clone.comment.dto.CommentRequestDto;
import com.example.catch_clone.comment.dto.CommentResponseDto;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.entity.User;
import java.util.List;

public interface CommentService {
  //댓글 생성
  StatusResponseDto addComment(User user, CommentRequestDto commentRequestDto,Long reviewId);
  //단일 댓글 불러오기
  CommentResponseDto getComment(Long commentId);
  //리뷰 댓글 리스트 불러오기
  List<CommentResponseDto> getReviewComments(Long reviewId);
  //유저의 댓글 리스트 가져오기
  List<CommentResponseDto> getUserComments(Long commentId);
  //댓글 수정하기
  StatusResponseDto updateComment(User user, Long commentId, CommentRequestDto commentRequestDto);
  //댓글 삭제하기
  StatusResponseDto deleteComment(User user, Long commentId);


}
