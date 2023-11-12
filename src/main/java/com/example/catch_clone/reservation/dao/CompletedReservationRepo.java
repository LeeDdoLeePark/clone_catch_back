package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.entity.CompletedReservation;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CompletedReservationRepo extends Repository<CompletedReservation,Long> {
//    List<CompletedReservation> findAllByUserId(String userId);
}
