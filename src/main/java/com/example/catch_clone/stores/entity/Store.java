package com.example.catch_clone.stores.entity;

import com.example.catch_clone.stores.dto.StoreDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Builder;
import lombok.CustomLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id")
  private Long id;

  @Column
  private String storeName; //가맹점이름

  @Column
  private String storeLocation; //가맹점위치정보

  @Column
  private Float starRate; //별점

  @Column
  private LocalTime timeDetail; // 변수명 변경 예정, 운영시간

  @Column
  private String storePhoneNumber;  //가게전화번호

  @Column
  private String aboutStore;  //가게소개

  @Column
  private LocalDateTime createdAt;  //생성일자

  @Column
  private String storeNotification; //가맹점공지

  @Column
  private ReservationTypeFlag reservationTypeFlag; //예약 지정일자 오픈 여부(Y,N)

  @Column
  private String regularHoliday; //정기 휴무일

  @Column
  private String storeHomepage; //홈페이지 주소

  @Builder
  public Store(String storeName, String storeLocation, Float starRate, LocalTime timeDetail, String storePhoneNumber, String aboutStore, LocalDateTime createdAt,String storeNotification,ReservationTypeFlag reservationTypeFlag, String regularHoliday,
      String storeHomepage) {

      this.storeName = storeName;
      this.storeLocation = storeLocation;
      this.starRate = starRate;
      this.timeDetail = timeDetail;
      this.storePhoneNumber = storePhoneNumber;
      this.aboutStore = aboutStore;
      this.createdAt = createdAt;
      this.storeNotification = storeNotification;
      this.reservationTypeFlag = reservationTypeFlag;
      this.regularHoliday = regularHoliday;
      this.storeHomepage = storeHomepage;

  }


  public Store(StoreDto storeDto){
    this.storeName = storeDto.storeName();
    this.storeLocation = storeDto.storeLocation();
    this.starRate = storeDto.starRate();
    this.timeDetail = LocalTime.now();
    this.storePhoneNumber = storeDto.storePhoneNumber();
    this.aboutStore = storeDto.aboutStore();
    this.createdAt = LocalDateTime.now();
    this.storeNotification = storeDto.storeNotification();
    this.reservationTypeFlag = storeDto.reservationTypeFlag();
    this.regularHoliday = storeDto.regularHoliday();
    this.storeHomepage = storeDto.storeHomepage();
  }

  public Store(String storeName){
    this.storeName = storeName;
  }
}
