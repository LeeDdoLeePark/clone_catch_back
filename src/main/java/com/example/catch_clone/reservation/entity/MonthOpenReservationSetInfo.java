package com.example.catch_clone.reservation.entity;

import com.example.catch_clone.stores.entity.Store;
import jakarta.persistence.*;

import java.time.DayOfWeek;

@Entity
public class MonthOpenReservationSetInfo {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    private String visitTime;    // 예약 시간

    private String reservationStatus;
    private Integer reservationCount; // 가능 인원

    // enum 으로 처리하는게 좋을 듯
    private DayOfWeek dayOfWeek;   // 요일

}
