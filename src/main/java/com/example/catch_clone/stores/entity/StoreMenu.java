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
public class StoreMenu {
  @Id
  @GeneratedValue
  private Long id; // pk없이 가맹점 ID를 사용할텐데 혹시 몰라 남겨놓습니다.

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String menuNm ; //메뉴이름

  @Column
  private String menuUrl ; //메뉴사진

  @Column
  private String menuPrice ; //메뉴가격

  @Column
  private String menuMain ; //메인메뉴



}
