package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;

import java.util.List;

public interface ReservationCustomRepo {
    List<ReservationSimpleResponseDto> getUserCompletedReservations(Long userId);

    List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId);

    List<ReservationSimpleResponseDto> findAllCanceledAndNoShowReservationByUserId(Long userId);
    List<ReservationSimpleResponseDto> getUserInProgressReservations(Long userId);
}
