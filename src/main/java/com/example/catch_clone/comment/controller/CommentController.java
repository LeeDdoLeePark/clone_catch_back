package com.example.catch_clone.comment.controller;

import static com.example.catch_clone.comment.controller.CommentController.COMMENT_URI_API;
import static com.example.catch_clone.review.controller.ReviewController.REVIEW_URI_API;

import com.example.catch_clone.comment.dto.CommentRequestDto;
import com.example.catch_clone.comment.dto.CommentResponseDto;
import com.example.catch_clone.comment.service.inter.CommentService;
import com.example.catch_clone.review.dto.ReviewRequestDto;
import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.security.UserDetailsImpl;
import com.example.catch_clone.security.dto.StatusResponseDto;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT_URI_API)
public class CommentController {
  public static final String COMMENT_URI_API = "/ct/comments";
  private final CommentService commentService;
  @PostMapping("/{reviewId}")
  public ResponseEntity<StatusResponseDto> addComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long reviewId ,@AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = commentService.addComment(userDetails.getUser(),commentRequestDto,reviewId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }

  @GetMapping("/{commentId}")
  public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId){
    CommentResponseDto commentResponseDto = commentService.getComment(commentId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(commentResponseDto);
  }

  @GetMapping("/{reviewId}")
  public ResponseEntity<List<CommentResponseDto>> getReviewComments(@PathVariable Long reviewId){
    List<CommentResponseDto> commentResponseDtos = commentService.getReviewComments(reviewId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(commentResponseDtos);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<CommentResponseDto>>getUserComments(@PathVariable Long userId){
    List<CommentResponseDto> commentResponseDtos = commentService.getUserComments(userId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(commentResponseDtos);
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<StatusResponseDto> updateComment(@PathVariable Long commentId, @AuthenticationPrincipal
  UserDetailsImpl userDetails, CommentRequestDto commentRequestDto){
    StatusResponseDto statusResponseDto = commentService.updateComment(userDetails.getUser(),commentId,commentRequestDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<StatusResponseDto> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = commentService.deleteComment(userDetails.getUser(),commentId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }

  //좋아요 컨트롤러
  @PostMapping("like/{commentId}")
  public ResponseEntity<StatusResponseDto> requestCommentLike(@PathVariable Long commentId,@AuthenticationPrincipal
  UserDetailsImpl userDetails){
    StatusResponseDto statusResponseDto = commentService.requestCommentLike(userDetails.getUserId(),commentId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }
}
