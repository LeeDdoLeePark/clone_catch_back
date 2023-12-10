package com.example.catch_clone.reservation.service;

import com.example.catch_clone.reservation.dao.ReservationRepository;
import com.example.catch_clone.reservation.dto.ReservationRequestDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.entity.Reservation;
import com.example.catch_clone.reservation.entity.ReservationStatus;
import com.example.catch_clone.reservation.service.inter.ReservationService;
import com.example.catch_clone.stores.entity.ReservationTypeFlag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        // 같은 예약이 존재하는지 확인하기
        boolean existReservation = reservationRepository.existsSameReservation(userId, request);

        if(existReservation == true){
            throw new RuntimeException("이미 예약이 존재합니다.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 문자열을 LocalDate로 변환
        LocalDate date = LocalDate.parse(request.getVisitDate(), formatter);

        // 요일 얻기
        DayOfWeek dayOfWeek = date.getDayOfWeek();


        // 예약 가능 인원 확인하기
        int possibleCount =0;

        // 분기 처리할 플래그
        if(request.getReservationTypeFlag().equals(ReservationTypeFlag.SPECIFIC_DATE)){
            // help
        }else{
            possibleCount = reservationRepository.getReservationSetCount(request, dayOfWeek);
        }

        int reservedCount = reservationRepository.getReservationTotalCount(request);

        if(possibleCount == reservedCount){
            throw new RuntimeException("죄송합니다. 모든 좌석이 예약되었습니다.");
        }
        else if(request.getReservationCount()<=possibleCount-reservedCount){
            Reservation reservation = new Reservation(request);
            reservationRepository.save(reservation);
        }else {
            throw new RuntimeException("예약 가능 인원이 요청 인원보다 적습니다.");
        }

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
