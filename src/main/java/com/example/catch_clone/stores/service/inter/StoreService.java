package com.example.catch_clone.stores.service.inter;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFilesDto;
import com.example.catch_clone.stores.dto.StoreMenuDto;
import com.example.catch_clone.stores.entity.StoreFiles;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface StoreService {

  StatusResponseDto addStore(StoreDto storeDto);

  StoreDto getShopInfo(Long storeId);

  StatusResponseDto addStoreMenu(StoreMenuDto storeMenuDto);

  StatusResponseDto addStoresFile(StoreFilesDto storeFilesDto);

  StoreMenuDto getMenuAllInfo(Long storeId);

  List<StoreFiles> getShopPhotoList(Long storeId);



  ResponseEntity<String> getShopMapInfo(Long storeId);

}
