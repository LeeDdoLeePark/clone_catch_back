package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.entity.InProgressReservation;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface InProgressReservationRepo extends Repository<InProgressReservation,Long> {
//    List<InProgressReservation> findAllByUserId(String userId);
}


