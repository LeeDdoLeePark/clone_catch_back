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
public class Two {


  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String reservationDayInfo;  //예약 오픈일

  @Column
  private String reservationStartDateInfo;  // 예약가능 시작일자

  @Column
  private String reservationFinishDateInfo; //예약가능 끝 일자

  @Column
  private String reservationTimeInfo; //예약가능 시간

  @Column
  private String field; //달 구분



}
