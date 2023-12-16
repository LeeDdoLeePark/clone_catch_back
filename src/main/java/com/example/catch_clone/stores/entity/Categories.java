package com.example.catch_clone.stores.entity;


import com.example.catch_clone.stores.dto.StoreCategoryDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Categories {
  @Id
  @GeneratedValue
  private Long id;


  @ManyToOne
  @JoinColumn(name="store_id")
  private Store store;

//  @Column
//  private Long storeId; //가맹점ID

  @Column
  private Long categoryCode; //카테코리코드( 예시:1.한식 , 2.양식, 3. 중식 )

  @Column
  private String categoryNm;  //카테고리이름

  @Builder
  public Categories(Store store, StoreCategoryDto storeCategoryDto){

    this.store = store;
    this.categoryCode = storeCategoryDto.categoryCode();
    this.categoryNm = storeCategoryDto.categoryNm();
  }



}
