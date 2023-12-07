package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.dto.ReservationRequestDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;

import java.time.DayOfWeek;
import java.util.List;

public interface ReservationCustomRepo {
    List<ReservationSimpleResponseDto> getUserCompletedReservations(Long userId);

    List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId);

    List<ReservationSimpleResponseDto> findAllCanceledAndNoShowReservationByUserId(Long userId);
    List<ReservationSimpleResponseDto> getUserInProgressReservations(Long userId);

    boolean existsSameReservation(Long userId, ReservationRequestDto request);

    int getReservationTotalCount(ReservationRequestDto request);
    int getReservationSetCount(ReservationRequestDto request, DayOfWeek dayOfWeek);
}
