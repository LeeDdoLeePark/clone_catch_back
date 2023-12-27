package com.example.catch_clone.reservation.controller;

import com.example.catch_clone.reservation.dto.RequestReservationDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.service.ReservationServiceImpl;
import com.example.catch_clone.security.UserDetailsImpl;
import com.example.catch_clone.security.dto.StatusResponseDto;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ct/stores")
public class ReservationController{

    private final ReservationServiceImpl reservationService;

    @GetMapping ("/a") // 유저 방문 완료 예약리스트 조회
    public List<ReservationSimpleResponseDto> getUserCompletedReservations(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return reservationService.getUserCompletedReservations(userDetails.getUserId());
    }

    @GetMapping("/b")
    public List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByLatest(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return reservationService.getUserCompletedReservationsSortedByOldest(userDetails.getUserId());
    }

    @GetMapping("/c")
    public void getUserCancelledAndNoShowReservations(@AuthenticationPrincipal UserDetailsImpl userDetails){
        reservationService.getUserCancelledAndNoShowReservations(userDetails.getUserId());
    }

    @GetMapping("/d")
    public void getUserReservationDetails(@AuthenticationPrincipal UserDetailsImpl userDetails){
        reservationService.getUserReservationDetails(userDetails.getUserId());
    }

    @GetMapping("/e")
    public void getUserInProgressReservations(@AuthenticationPrincipal UserDetailsImpl userDetails){
        reservationService.getUserCancelledAndNoShowReservations(userDetails.getUserId());
    }

    @PostMapping// 가게 예약
    public void createReservation(@AuthenticationPrincipal UserDetailsImpl userDetails){
        reservationService.createReservation(userDetails.getUserId());
    }

    @PutMapping
    public void updateReservationToCancelled(@AuthenticationPrincipal UserDetailsImpl userDetails){
        reservationService.updateReservationToCancelled(userDetails.getUserId());
    }

    @PutMapping("/cf")
    public void updateReservationToCompleted(@AuthenticationPrincipal UserDetailsImpl userDetails){
        reservationService.updateReservationToCompleted(userDetails.getUserId());
    }

    @PostMapping("/reservation/{storeId}/{storeReservationId}")
    public ResponseEntity<StatusResponseDto> requestReservation(@AuthenticationPrincipal UserDetailsImpl userDetails,Long storeId,Long storeReservationId,
        RequestReservationDto requestReservationDto){
        String result = reservationService.requestReservation(requestReservationDto,storeId,storeReservationId,
            userDetails.getUserId());
        return Objects.equals(result, "OK") ? ResponseEntity
            .status(HttpStatus.OK).body(new StatusResponseDto(200,"Success")) : ResponseEntity.status(
            HttpStatus.BAD_REQUEST).build();
    }

}
