package com.example.catch_clone.reservation.service.inter;

import com.example.catch_clone.reservation.dto.ReservationRequestDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<ReservationSimpleResponseDto> getUserCompletedReservations(Long userId);

    List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId);

    List<ReservationSimpleResponseDto> getUserCancelledAndNoShowReservations(Long userId);

    void getUserReservationDetails(Long userId);

    void createReservation(Long userId, ReservationRequestDto request);

    void updateReservationToCancelled(Long userId, Long reservationId);

    void updateReservationToCompleted(Long userId, Long reservationId);

    List<ReservationSimpleResponseDto> getUserInProgressReservations(Long userId);
}
