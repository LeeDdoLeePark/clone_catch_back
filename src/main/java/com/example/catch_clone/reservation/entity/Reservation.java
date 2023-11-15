package com.example.catch_clone.reservation.entity;

import com.example.catch_clone.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reservation extends TimeStamped {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long userId;  //회원ID

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String reservationStatus;  //예약상태

  @OneToOne
  @JoinColumn(name = "reservation_status_info_id")
  private ReservationStatusInfo reservationStatusInfo;
}
