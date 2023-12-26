package com.example.catch_clone.reservation.service.inter;

import com.example.catch_clone.reservation.dto.StoreReservationAddDto;
import com.example.catch_clone.security.dto.StatusResponseDto;

public interface StoreReservationService {
  StatusResponseDto addStoreReservation(StoreReservationAddDto storeReservationAddDto,Long StoreId);
}
