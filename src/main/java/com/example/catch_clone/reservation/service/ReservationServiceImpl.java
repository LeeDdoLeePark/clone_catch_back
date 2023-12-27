package com.example.catch_clone.reservation.service;

import com.example.catch_clone.reservation.dao.ReservationRepository;
import com.example.catch_clone.reservation.dto.RequestReservationDto;
import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.entity.Reservation;
import com.example.catch_clone.reservation.entity.StoreReservationDayInfo;
import com.example.catch_clone.reservation.entity.StoreReservationInfo;
import com.example.catch_clone.reservation.service.inter.ReservationService;
import com.example.catch_clone.reservation.service.inter.StoreReservationService;
import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.user.entity.User;
import com.example.catch_clone.user.service.inter.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final StoreReservationService storeReservationService;
    private final UserService userService;

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

    @Override
    @Transactional
    public String requestReservation(RequestReservationDto requestReservationDto,
        Long storeId,Long storeReservationInfoId, Long userId) {
        User user = userService.findById(userId);
        StoreReservationInfo storeReservationInfo = storeReservationService.findById(storeReservationInfoId);
        String updateReservationDayInfo = normalizationStoreReservationDayInfos(storeReservationInfo.getStoreReservationDayInfos(),requestReservationDto);
        if(updateReservationDayInfo.equals("imPossible")) return "BAD_REQUEST";
        Reservation reservation = Reservation.builder()
            .storeId(storeId)
            .userId(user.getId())
            .reservationStatus("processing")
            .build();
        storeReservationInfo.update(updateReservationDayInfo);
        reservationRepository.save(reservation);
        return "OK";
    }

    private String normalizationStoreReservationDayInfos(String storeReservationDayInfos,RequestReservationDto requestReservationDto){
        Gson gson = new Gson();
        //데이터 정규화
        Set<StoreReservationDayInfo> storeReservationDayInfoSet = gson.fromJson(storeReservationDayInfos, new TypeToken<Set<StoreReservationDayInfo>>() {}.getType());
        StoreReservationDayInfo storeReservationDayInfo = new StoreReservationDayInfo(
            requestReservationDto.day(),requestReservationDto.times(),true,requestReservationDto.capacity());
        //일치하는 예약정보가 없으면 불가능 반환
        if(!storeReservationDayInfoSet.contains(storeReservationDayInfo)) return "imPossible";

        storeReservationDayInfoSet.remove(storeReservationDayInfo);
        storeReservationDayInfo.updateStoreReservationDayInfo();
        storeReservationDayInfoSet.add(storeReservationDayInfo);
        return gson.toJson(storeReservationDayInfoSet);
    }
}
