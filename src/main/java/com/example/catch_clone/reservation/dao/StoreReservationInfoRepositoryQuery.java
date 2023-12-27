package com.example.catch_clone.reservation.dao;

public interface StoreReservationInfoRepositoryQuery {
  boolean existsStoreReservationInfoByYearsAndMonths(Short years, Byte months);

}
