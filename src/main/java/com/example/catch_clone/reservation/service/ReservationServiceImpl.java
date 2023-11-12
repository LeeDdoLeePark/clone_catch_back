package com.example.catch_clone.reservation.service;

import com.example.catch_clone.reservation.dao.ReservationRepository;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
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
        List<ReservationSimpleResponseDto> rst = reservationRepository.findAllReservationByUserId(userId);
        return rst;
    }

    // 유저 아이디로 예약 리스트 조회 ( 정렬 desc ) _ 나중에 동적 정렬로 위랑 하나의 쿼리 사용하기
    @Override
    public List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId) {
        List<ReservationSimpleResponseDto> rst = reservationRepository.getUserCompletedReservationsSortedByOldest(userId);
        return rst;
    }

    @Override
    public void getUserCancelledAndNoShowReservations(Long userId) {

    }

    @Override
    public void getUserReservationDetails(Long userId) {

    }

    @Override
    public void createReservation(Long userId) {

    }

    @Override
    public void updateReservationToCancelled(Long userId) {

    }

    @Override
    public void updateReservationToCompleted(Long userId) {

    }
}
