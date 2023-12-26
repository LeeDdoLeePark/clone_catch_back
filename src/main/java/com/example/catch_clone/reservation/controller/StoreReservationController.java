package com.example.catch_clone.reservation.controller;


import com.example.catch_clone.reservation.dto.StoreReservationAddDto;
import com.example.catch_clone.reservation.service.inter.StoreReservationService;
import com.example.catch_clone.security.dto.StatusResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("ct/stores/reservation")
public class StoreReservationController {
  private final StoreReservationService storeReservationService;
  @PostMapping("/{storeId}")
  public ResponseEntity<StatusResponseDto> addReservation(@RequestBody StoreReservationAddDto storeReservationAddDto, @PathVariable Long storeId){
    StatusResponseDto statusResponseDto = storeReservationService.addStoreReservation(storeReservationAddDto,storeId);
    return ResponseEntity.ok().body(statusResponseDto);
  }

}
