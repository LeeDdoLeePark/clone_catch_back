package com.example.catch_clone.comment.dao;

import com.example.catch_clone.comment.entity.Comment;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CommentRepository extends Repository<Comment,Long>,CommentRepositoryQuery {
  void save(Comment comment);
  Optional<Comment> findById(Long commentId);
  void deleteById(Long commentId);
}
