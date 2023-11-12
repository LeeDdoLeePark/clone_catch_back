package com.example.catch_clone.stores.dto;

import com.example.catch_clone.stores.entity.Store;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record StoreDto(String storeName, String storeLocation, Float starRate, LocalTime timeDetail, String storePhoneNumber, String aboutStore,
                       LocalDateTime createdAt, String storeNotification,String reservationTypeFlag,String regularHoliday,String storeHomepage) {


  public static StoreDto valueOf(Store store){
    return new StoreDto(store.getStoreName(),store.getStoreLocation(),store.getStarRate(),store.getTimeDetail(),store.getStorePhoneNumber(),store.getAboutStore(),
        store.getCreatedAt(),store.getStoreNotification(),store.getReservationTypeFlag(),store.getRegularHoliday(),store.getStoreHomepage());
  }
}



