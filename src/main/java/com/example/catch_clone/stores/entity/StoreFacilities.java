package com.example.catch_clone.stores.entity;


import com.example.catch_clone.stores.dto.StoreFacilityDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class StoreFacilities {
  @Id
  @GeneratedValue
  private Long id;


  @ManyToOne
  @JoinColumn(name="store_id")
  private Store store;

//  @Column
//  private Long storeId; //가맹점ID

  @Column
  private Long facilityNum; //가게편의시설 코드( 예시:1.주차장 , 2.놀이시설, 3.노키즈 )

  @Column
  private String facilityNm;  //가게편의시설 이름


  @Builder
  public StoreFacilities(Store store, StoreFacilityDto storeFacilityDto){
    this.store = store;
    this.facilityNum = storeFacilityDto.facilityNum();
    this.facilityNm = storeFacilityDto.facilityNm();
  }

}
