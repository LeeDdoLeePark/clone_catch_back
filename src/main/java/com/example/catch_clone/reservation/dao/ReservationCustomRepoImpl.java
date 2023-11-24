package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.dto.ReservationSimpleResponseDto;
import com.example.catch_clone.reservation.entity.ReservationStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.catch_clone.reservation.entity.QReservation.reservation;
import static com.example.catch_clone.stores.entity.QStore.store;
import static com.example.catch_clone.stores.entity.QCategories.categories;

@RequiredArgsConstructor
public class ReservationCustomRepoImpl implements ReservationCustomRepo{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReservationSimpleResponseDto> getUserCompletedReservations(Long userId) {
        return queryFactory
                .select(
                        Projections.bean(
                                ReservationSimpleResponseDto.class,
                                store.storeName
                                ,categories.categoryNm
                                ,reservation.reservationDate
                                ,reservation.reservationTime
                                ,reservation.reservationCount
                        )
                )
                .from(reservation)
                .join(store)
                .join(categories)
                .where(
                        store.id.eq(reservation.store.id)
                        .and(categories.storeId.eq(store.id))
                        .and(reservation.user.id.eq(userId))
                        .and(reservation.reservationStatus.eq(ReservationStatus.COMPLETED))
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
                                ,reservation.reservationDate
                                ,reservation.reservationTime
                                ,reservation.reservationCount
                        )
                )
                .from(reservation)
                .join(store)
                .join(categories)
                .where(
                        store.id.eq(reservation.store.id)
                                .and(categories.storeId.eq(store.id))
                                .and(reservation.user.id.eq(userId))
                                .and(reservation.reservationStatus.eq(ReservationStatus.COMPLETED))
                )
                .orderBy(reservation.createdAt.desc())
                .fetch();
    }

    @Override
    public List<ReservationSimpleResponseDto> findAllCanceledAndNoShowReservationByUserId(Long userId) {

        return queryFactory
                .select(
                        Projections.bean(
                                ReservationSimpleResponseDto.class,
                                store.storeName
                                ,categories.categoryNm
                                ,reservation.reservationDate
                                ,reservation.reservationTime
                                ,reservation.reservationCount
                        )
                )
                .from(reservation)
                .join(store)
                .join(categories)
                .where(
                        store.id.eq(reservation.store.id)
                                .and(categories.storeId.eq(store.id))
                                .and(reservation.user.id.eq(userId))
                                .and(reservation.reservationStatus.in(ReservationStatus.NO_SHOW,
                                                                      ReservationStatus.CANCELED))
                )
                .orderBy(reservation.createdAt.desc())
                .fetch();

    }

    @Override
    public List<ReservationSimpleResponseDto> getUserInProgressReservations(Long userId) {
        return queryFactory
                .select(
                        Projections.bean(
                                ReservationSimpleResponseDto.class,
                                store.storeName
                                ,categories.categoryNm
                                ,reservation.reservationDate
                                ,reservation.reservationTime
                                ,reservation.reservationCount
                        )
                )
                .from(reservation)
                .join(store)
                .join(categories)
                .where(
                        store.id.eq(reservation.store.id)
                                .and(categories.storeId.eq(store.id))
                                .and(reservation.user.id.eq(userId))
                                .and(reservation.reservationStatus.in(ReservationStatus.IN_PROGRESS))
                )
                .orderBy(reservation.createdAt.desc())
                .fetch();
    }
}
