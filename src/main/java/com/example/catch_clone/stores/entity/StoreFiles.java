package com.example.catch_clone.stores.entity;


import com.example.catch_clone.stores.dto.StoreFilesDto;
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
import org.springframework.cglib.core.Local;

@Getter
@NoArgsConstructor
@Entity
public class StoreFiles {
  @Id
  @GeneratedValue
  private Long id;


  @ManyToOne
  @JoinColumn(name="store_id")
  private Store store;
//  @Column
//  private Long storeId; //가맹점ID

  @Column
  private String fileUrl; //파일URL

  @Column
  private LocalDateTime createdAt;  //생성일자

  @Builder
  public StoreFiles(Store store,String fileUrl, LocalDateTime createdAt){
    this.store = store;
    this.fileUrl = fileUrl;
    this.createdAt = createdAt;
  }

  public StoreFiles(Store store,StoreFilesDto storeFilesDto){
    this.store = store;
    this.fileUrl = storeFilesDto.fileUrl();
    this.createdAt = LocalDateTime.now();
  }



}
