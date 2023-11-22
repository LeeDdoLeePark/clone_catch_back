package com.example.catch_clone.stores.dao;

import com.example.catch_clone.comment.entity.Comment;
import com.example.catch_clone.reservation.entity.Reservation;
import com.example.catch_clone.review.entity.Review;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.entity.Store;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface StoreRepository extends Repository<Store,Long> {

    void save(Store store);

    Optional<Store> findById(Long storeId);


}
