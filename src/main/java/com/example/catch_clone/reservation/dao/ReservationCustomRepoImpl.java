package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.entity.CanceledReservation;
import com.example.catch_clone.reservation.entity.QCanceledReservation;
import com.example.catch_clone.stores.entity.QCategories;
import com.example.catch_clone.stores.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.catch_clone.reservation.entity.QCanceledReservation.canceledReservation;
import static com.example.catch_clone.reservation.entity.QCompletedReservation.completedReservation;
import static com.example.catch_clone.reservation.entity.QNoShowReservation.noShowReservation;
import static com.example.catch_clone.reservation.entity.QReservation.reservation;
import static com.example.catch_clone.stores.entity.QStore.store;
import static com.example.catch_clone.stores.entity.QCategories.categories;

@RequiredArgsConstructor
public class ReservationCustomRepoImpl implements ReservationCustomRepo{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReservationSimpleResponseDto> findAllReservationByUserId(Long userId) {
        return queryFactory
                .select(
                        Projections.bean(
                                ReservationSimpleResponseDto.class,
                                store.storeName
                                ,categories.categoryNm
                                ,completedReservation.reservationDate
                                ,completedReservation.reservationTime
                                ,completedReservation.reservationPersonnel
                                ,reservation.reservationStatus
                        )
                )
                .from(reservation)
                .join(completedReservation)
                .join(store)
                .join(categories)
                .where(completedReservation.id.eq(reservation.id)
                        .and(store.id.eq(reservation.storeId))
                        .and(categories.storeId.eq(store.id))
                )
                .fetch();
    }

    @Override
    public List<ReservationSimpleResponseDto> getUserCompletedReservationsSortedByOldest(Long userId) {
        return queryFactory
                .select(
                        Projections.bean(
                                ReservationSimpleResponseDto.class,
                                store.storeName
                                ,categories.categoryNm
                                ,completedReservation.reservationDate
                                ,completedReservation.reservationTime
                                ,completedReservation.reservationPersonnel
                                ,reservation.reservationStatus
                        )
                )
                .from(reservation)
                .join(completedReservation)
                .join(store)
                .join(categories)
                .where(completedReservation.id.eq(reservation.id)
                        .and(store.id.eq(reservation.storeId))
                        .and(categories.storeId.eq(store.id))
                )
                .orderBy(reservation.createdAt.desc())
                .fetch();
    }

    @Override
    public List<ReservationSimpleResponseDto> findAllCanceledAndNoShowReservationByUserId(Long userId) {

        return queryFactory
                .select(
                        Projections.bean(
                                ReservationSimpleResponseDto.class

                        )
                )
                .from(reservation)
                .join(canceledReservation)
                .join(noShowReservation)
                .join(store)
                .join(categories)
                .where(reservation.id.eq(canceledReservation.id)
                        .and(reservation.id.eq(noShowReservation.id))
                        .and(store.id.eq(reservation.storeId))
                        .and(categories.storeId.eq(store.id))
                )
                .fetch();

    }
}
