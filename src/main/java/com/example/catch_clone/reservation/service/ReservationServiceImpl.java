package com.example.catch_clone.reservation.service;

import com.example.catch_clone.reservation.dao.ReservationRepository;
import com.example.catch_clone.reservation.dto.ReservationRequestDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.entity.Reservation;
import com.example.catch_clone.reservation.entity.ReservationStatus;
import com.example.catch_clone.reservation.service.inter.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;


    // 유저 아이디로 예약 리스트 조회하기
    @Override
    public List<ReservationSimpleResponseDto> getUserCompletedReservations(Long userId) {
        // 리뷰 가능 여부가 컬럼에 들어가야 하는가?
        return reservationRepository.getUserCompletedReservations(userId);
    }

    // 유저 아이디로 예약 리스트 조회 ( 정렬 desc ) _ 나중에 동적 정렬로 위랑 하나의 쿼리 사용하기
    @Override
    public List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId) {
        return reservationRepository.getUserCompletedReservationsSortedByOldest(userId);
    }

    @Override
    public List<ReservationSimpleResponseDto> getUserCancelledAndNoShowReservations(Long userId) {
        return reservationRepository.findAllCanceledAndNoShowReservationByUserId(userId);
    }

    @Override
    public List<ReservationSimpleResponseDto> getUserInProgressReservations(Long userId) {
        return reservationRepository.getUserInProgressReservations(userId);
    }

    @Override
    public void getUserReservationDetails(Long userId) {

    }

    public void createReservation(Long userId, ReservationRequestDto request) {
        Reservation reservation = new Reservation(request);
        reservationRepository.save(reservation);
    }

    @Override
    public void updateReservationToCancelled(Long userId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(IllegalArgumentException::new);
        reservation.changeReservationStatus(ReservationStatus.CANCELED);

    }

    @Override
    public void updateReservationToCompleted(Long userId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(IllegalArgumentException::new);
        reservation.changeReservationStatus(ReservationStatus.COMPLETED);
    }


}
