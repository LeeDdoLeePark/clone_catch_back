package com.example.catch_clone.stores.dto;

import com.example.catch_clone.stores.entity.ReservationTypeFlag;
import com.example.catch_clone.stores.entity.Store;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record StoreDto(String storeName, String storeLocation, Float starRate, String storePhoneNumber, String aboutStore,
                       String storeNotification, ReservationTypeFlag reservationTypeFlag, String regularHoliday, String storeHomepage) {


  public static StoreDto valueOf(Store store){
    return new StoreDto(store.getStoreName(),store.getStoreLocation(),store.getStarRate(),store.getStorePhoneNumber(),store.getAboutStore(),
       store.getStoreNotification(),store.getReservationTypeFlag(),store.getRegularHoliday(),store.getStoreHomepage());
  }
}



