package com.example.catch_clone.reservation.service;

import com.example.catch_clone.reservation.dao.StoreReservationInfoRepository;
import com.example.catch_clone.reservation.dto.StoreReservationAddDto;
import com.example.catch_clone.reservation.entity.StoreReservationInfo;
import com.example.catch_clone.reservation.service.inter.StoreReservationService;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.stores.entity.Store;
import com.example.catch_clone.stores.service.inter.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreReservationServiceImpl implements StoreReservationService {
  private final StoreService storeService;
  private final StoreReservationInfoRepository storeReservationInfoRepository;
  @Override
  public StatusResponseDto addStoreReservation(StoreReservationAddDto storeReservationAddDto,
      Long storeId) {
    Store store = storeService.findById(storeId);

    if(storeReservationInfoRepository.existsStoreReservationInfoByYearsAndMonths(
        storeReservationAddDto.years(),storeReservationAddDto.months())){
      throw new IllegalArgumentException("이미 존재하는 예약입니다");
    }

    StoreReservationInfo storeReservationInfo = StoreReservationInfo.builder()
        .store(store)
        .months(storeReservationAddDto.months())
        .years(storeReservationAddDto.years())
        .storeReservationDayInfos(storeReservationAddDto.StoreReservationDayinfos())
        .build();

    storeReservationInfoRepository.save(storeReservationInfo);
    return new StatusResponseDto(201,"Created");
  }
}
