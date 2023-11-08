package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.entity.CanceledReservation;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CanceledReservationRepo extends Repository<CanceledReservation,Long> {
//    List<CanceledReservation> findAllByUserId(String userId);
}
