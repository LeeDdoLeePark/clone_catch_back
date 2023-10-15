package com.example.catch_clone.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class CommentLike {
  @Id
  @GeneratedValue
  private Long id;  //여기도 댓글ID,회원ID를 fk로 가지고 있고 pk가 따로 없는데 혹시 몰라 남겨놓습니다.
  //사실 댓글의 총 카운트 쿼리를 날린다면 인덱스를 활용하는 것이 효율적으로 좋을텐데, Pk를 활용하는 것이 좋지 않을까 싶습니다.

  @Column
  private Long userId;  //유저ID

  @Column
  private Long commentId; //댓글ID


}
