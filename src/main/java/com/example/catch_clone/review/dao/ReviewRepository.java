package com.example.catch_clone.review.dao;

import com.example.catch_clone.review.entity.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReviewRepository extends Repository<Review,Long> {
  void save(Review review);
  Optional<Review> findById(Long reviewId);
  List<Review> findAllByStoreId(Long storeId);
  List<Review> findAllByUserId(Long userId);
  void deleteById(Long reviewId);
}
