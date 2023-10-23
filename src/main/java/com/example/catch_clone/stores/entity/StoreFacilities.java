package com.example.catch_clone.stores.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class StoreFacilities {
  @Id
  @GeneratedValue
  private Long id; // pk없이 가맹점 ID를 사용할텐데 혹시 몰라 남겨놓습니다.

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String facilityNum; //가게편의시설 코드

  @Column
  private String facilityNm;  //가게편의시설 이름



}
