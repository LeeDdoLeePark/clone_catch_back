package com.example.catch_clone.reservation.entity;

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
public class Reservation {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long userId;  //회원ID

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String reservationStatus;  //예약상태

  @Column
  private LocalDateTime createdAt;  //생성일자

}
