package com.example.catch_clone.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReviewLikeId implements Serializable {

  @Serial
  private static final long serialVersionUID = 932813899396736126L;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "reveiw_id")
  private Long reveiwId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewLikeId reviewLikeId = (ReviewLikeId) o;
    return Objects.equals(getUserId(), reviewLikeId.getUserId()) && Objects.equals(getReveiwId(),
        reviewLikeId.getReveiwId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getReveiwId());
  }
}
