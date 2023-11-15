package com.example.catch_clone.reservation.entity;

import com.example.catch_clone.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ReservationStatusInfo extends TimeStamped {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String reservationPersonnel;  //예약인원

  @Column
  private String reservationDate; //예약일자

  @Column
  private String reservationTime; //예약시간

//4개의 boolean 값으로 상태 구분
  @Column
  private boolean inProgress;

  @Column
  private boolean noShow;

  @Column
  private boolean completed;

  @Column
  private boolean canceled;

  @OneToOne(mappedBy = "reservationStatusInfo")
  private Reservation reservation;

  @Builder
  public ReservationStatusInfo(Long userId, Long storeId, String reservationStatus){
  }
}
