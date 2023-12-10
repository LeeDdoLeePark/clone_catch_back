package com.example.catch_clone.reservation.entity;

import com.example.catch_clone.reservation.dto.ReservationRequestDto;
import com.example.catch_clone.stores.entity.Store;
import com.example.catch_clone.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
  @Id
  @GeneratedValue
  @Column(name = "reservation_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "store_id")
  private Store store;

  @Column
  private Integer reservationCount;  //예약인원

  @Column
  private String reservationDate; //예약일자

  @Column
  private String reservationTime; //예약시간

  @Column
  private LocalDateTime createdAt;  //생성일자

  @Column
  @Enumerated(EnumType.STRING)
  private ReservationStatus reservationStatus;  //예약상태

  // 캡슐화 필요

  public Reservation(ReservationRequestDto request) {
    this.reservationCount = request.getReservationCount();
    this.reservationDate = request.getVisitDate();
    this.reservationTime = request.getVisitTime();
    this.createdAt = LocalDateTime.now();
    this.reservationStatus = ReservationStatus.IN_PROGRESS;
  }
  public void changeReservationStatus(ReservationStatus reservationStatus) {
    this.reservationStatus = reservationStatus;
  }
}
