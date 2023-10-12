package com.example.catch_clone.bookmark.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Bookmark {
  @Id
  @GeneratedValue
  private Long id;

}
