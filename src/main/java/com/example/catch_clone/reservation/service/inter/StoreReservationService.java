package com.example.catch_clone.reservation.service.inter;

import com.example.catch_clone.reservation.dto.StoreReservationAddDto;
import com.example.catch_clone.reservation.entity.StoreReservationInfo;
import com.example.catch_clone.security.dto.StatusResponseDto;
import java.util.Optional;

public interface StoreReservationService {
  StatusResponseDto addStoreReservation(StoreReservationAddDto storeReservationAddDto,Long StoreId);

  StoreReservationInfo findById(Long storeReservationInfoId);
}
