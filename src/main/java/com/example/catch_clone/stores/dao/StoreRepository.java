package com.example.catch_clone.stores.dao;

import com.example.catch_clone.comment.entity.Comment;
import com.example.catch_clone.reservation.entity.Reservation;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.stores.entity.Store;
import org.springframework.data.repository.Repository;

public interface StoreRepository extends Repository<Store,Long> {

}
