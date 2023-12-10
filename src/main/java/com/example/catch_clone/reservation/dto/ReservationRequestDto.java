package com.example.catch_clone.reservation.dto;


import com.example.catch_clone.stores.entity.ReservationTypeFlag;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class ReservationRequestDto {

    private Long storeId;
    // yyMMdd 로 포맷 지정해야함
    private String visitDate;
    private String visitTime;
    private Integer reservationCount;

    private ReservationTypeFlag reservationTypeFlag;
}
