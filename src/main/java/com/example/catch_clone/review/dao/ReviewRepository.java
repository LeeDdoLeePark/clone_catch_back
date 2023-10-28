package com.example.catch_clone.review.dao;

import com.example.catch_clone.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
  List<Review> findAllByStoreId(Long storeId);
  List<Review> findAllByUserId(Long userId);
}
