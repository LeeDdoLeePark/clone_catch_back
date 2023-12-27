package com.example.catch_clone.reservation.service.inter;

import com.example.catch_clone.reservation.dto.RequestReservationDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;

import com.example.catch_clone.security.dto.StatusResponseDto;
import java.util.List;

public interface ReservationService {
    List<ReservationSimpleResponseDto> getUserCompletedReservations(Long userId);

    List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId);

    void getUserCancelledAndNoShowReservations(Long userId);

    void getUserReservationDetails(Long userId);

    void createReservation(Long userId);

    void updateReservationToCancelled(Long userId);

    void updateReservationToCompleted(Long userId);

    String requestReservation(RequestReservationDto requestReservationDto,Long storeId, Long storeReservationId,Long userId);
}
