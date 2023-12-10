package com.example.catch_clone.reservation.controller;

import com.example.catch_clone.reservation.dto.ReservationRequestDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.service.ReservationServiceImpl;
import com.example.catch_clone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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
    public List<ReservationSimpleResponseDto> getUserCancelledAndNoShowReservations(@AuthenticationPrincipal UserDetailsImpl userDetails){
       return reservationService.getUserCancelledAndNoShowReservations(userDetails.getUserId());
    }

    @GetMapping("/d")
    public void getUserReservationDetails(@AuthenticationPrincipal UserDetailsImpl userDetails){
        reservationService.getUserReservationDetails(userDetails.getUserId());
    }

    @GetMapping("/e")
    public List<ReservationSimpleResponseDto> getUserInProgressReservations(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return reservationService.getUserInProgressReservations(userDetails.getUserId());
    }

    @PostMapping// 가게 예약
    public void createReservation(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @RequestBody ReservationRequestDto request){
        reservationService.createReservation(userDetails.getUserId(), request);
    }

    @PutMapping
    public void updateReservationToCancelled(@AuthenticationPrincipal UserDetailsImpl userDetails, Long id){
        reservationService.updateReservationToCancelled(userDetails.getUserId(), id);
    }

    @PutMapping("/cf")
    public void updateReservationToCompleted(@AuthenticationPrincipal UserDetailsImpl userDetails, Long id){
        reservationService.updateReservationToCompleted(userDetails.getUserId(), id);
    }

}
