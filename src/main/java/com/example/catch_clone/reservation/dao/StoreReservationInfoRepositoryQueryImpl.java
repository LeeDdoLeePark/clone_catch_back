package com.example.catch_clone.reservation.dao;

import static com.example.catch_clone.test.QStoreReservationInfo.storeReservationInfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StoreReservationInfoRepositoryQueryImpl implements StoreReservationInfoRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  public boolean existsStoreReservationInfoByYearsAndMonths(Short years, Byte months) {
    return jpaQueryFactory.from(storeReservationInfo).where(storeReservationInfo.years.eq(years),storeReservationInfo.months.eq(months)).select(storeReservationInfo.years,storeReservationInfo.months)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }

}
