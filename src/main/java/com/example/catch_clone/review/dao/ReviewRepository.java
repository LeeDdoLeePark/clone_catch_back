package com.example.catch_clone.review.dao;

import com.example.catch_clone.comment.entity.Comment;
import com.example.catch_clone.reservation.entity.Reservation;
import com.example.catch_clone.review.entity.Review;
import org.springframework.data.repository.Repository;

public interface ReviewRepository extends Repository<Review,Long> {

}
