package com.example.catch_clone.stores.service.inter;

import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFilesResponseDto;
import com.example.catch_clone.stores.dto.StoreMenuResponseDto;
import com.example.catch_clone.stores.entity.StoreFiles;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface StoreService {

  StoreDto getShopInfo(Long storeId);

  StoreMenuResponseDto getMenuAllInfo(Long storeId);

  List<StoreFiles> getShopPhotoList(Long storeId);

  ResponseEntity<String> getShopMapInfo(Long storeId);

}
