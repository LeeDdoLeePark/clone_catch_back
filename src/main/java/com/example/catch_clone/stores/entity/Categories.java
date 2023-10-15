package com.example.catch_clone.stores.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Categories {
  @Id
  @GeneratedValue
  private Long id; // pk없이 가맹점 ID를 사용할텐데 혹시 몰라 남겨놓습니다.

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String categoryId; //카테코리코드

  @Column
  private String categoryNm;  //카테고리이름



}
