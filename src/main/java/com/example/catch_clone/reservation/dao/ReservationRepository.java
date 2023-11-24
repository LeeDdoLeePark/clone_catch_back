package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.entity.Reservation;
import com.example.catch_clone.reservation.entity.ReservationStatus;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends Repository<Reservation,Long>, ReservationCustomRepo {

    List<Reservation> findAllByReservationStatus(ReservationStatus status);
    Optional<Reservation> findById(Long id);
    void save(Reservation reservation);
}
