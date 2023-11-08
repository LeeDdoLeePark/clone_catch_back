package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.entity.NoShowReservation;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface NoShowReservationRepo extends Repository<NoShowReservation,Long> {
//    List<NoShowReservation> findAllByUserId(String userId);
}
