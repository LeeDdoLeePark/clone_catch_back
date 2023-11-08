package com.example.catch_clone.reservation.dao;

import com.example.catch_clone.reservation.entity.Reservation;
import org.springframework.data.repository.Repository;

public interface ReservationRepository extends Repository<Reservation,Long>, ReservationCustomRepo {

}
