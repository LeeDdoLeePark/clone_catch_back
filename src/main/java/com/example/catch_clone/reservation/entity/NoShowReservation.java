package com.example.catch_clone.reservation.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class NoShowReservation {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String reservationPersonnel;  //예약인원

  @Column
  private String reservationDate; //예약일자

  @Column
  private String reservationTime; //예약시간

  @Column
  private LocalDateTime createdAt;  //생성일자

}
