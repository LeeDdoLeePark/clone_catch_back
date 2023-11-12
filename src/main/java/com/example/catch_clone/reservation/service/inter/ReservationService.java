package com.example.catch_clone.reservation.service.inter;

import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;

import java.util.List;

public interface ReservationService {
    List<ReservationSimpleResponseDto> getUserCompletedReservations(Long userId);

    List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId);

    void getUserCancelledAndNoShowReservations(Long userId);

    void getUserReservationDetails(Long userId);

    void createReservation(Long userId);

    void updateReservationToCancelled(Long userId);

    void updateReservationToCompleted(Long userId);
}
