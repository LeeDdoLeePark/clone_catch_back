package com.example.catch_clone.reservation.dto;

import lombok.Builder;

@Builder
public class ReservationSimpleResponseDto {

    private String storeName;
    private String category;
    // private String location;
    private String visitDate;
    private String visitTime;
    private String reservationCount;
    private String reviewFlag; // 리뷰 가능 여부
}
