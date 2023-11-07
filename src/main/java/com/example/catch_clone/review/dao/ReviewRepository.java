package com.example.catch_clone.review.dao;

import com.example.catch_clone.review.entity.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Review.class, idClass = Long.class)
public interface ReviewRepository extends Repository<Review,Long>, ReviewRepositoryQuery {
  void save(Review review);
  Optional<Review> findById(Long reviewId);
  List<Review> findAllByStoreId(Long storeId);
  List<Review> findAllByUserId(Long userId);
  void deleteById(Long reviewId);
}
