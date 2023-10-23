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
public class Three {


  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long reservationInfoId; //예약정보ID

  /*
  @Column
  private Long reservationMonthInfo;  //가맹점ID
  박상훈 : 아마 이 가맹점 컬럼은 지우기로 한 것 같은데 ERD에 남아있어 주석으로 달아놓습니다.
  */

  @Column
  private String reservationVisitTIme;  //방문시간

  @Column
  private String reservationStatus; //가능여부

  @Column
  private String reservation_people;  //인원수

  @Column
  private String dayOfTheWeek;  //요일(일주일)




}
