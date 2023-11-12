package com.example.catch_clone.stores.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record StoreDto(String storeName, String storeLocation, Float starRate, LocalTime timeDetail, String storePhoneNumber, String aboutStore,
                       LocalDateTime createdAt, String storeNotification,String reservationTypeFlag,String regularHoliday,String storeHomepage) {

}
