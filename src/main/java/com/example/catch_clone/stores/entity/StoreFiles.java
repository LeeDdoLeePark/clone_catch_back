package com.example.catch_clone.stores.entity;


import com.example.catch_clone.stores.dto.StoreFilesDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

  @Column
  private Long storeId; //가맹점ID

  @Column
  private String fileUrl; //파일URL

  @Column
  private LocalDateTime createdAt;  //생성일자

  @Builder
  public StoreFiles(Long storeId,String fileUrl, LocalDateTime createdAt){
    this.storeId = storeId;
    this.fileUrl = fileUrl;
    this.createdAt = createdAt;
  }

  public StoreFiles(StoreFilesDto storeFilesDto){
    this.storeId = storeFilesDto.storeId();
    this.fileUrl = storeFilesDto.fileUrl();
    this.createdAt = LocalDateTime.now();
  }



}
