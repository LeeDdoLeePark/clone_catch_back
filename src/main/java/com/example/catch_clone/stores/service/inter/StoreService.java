package com.example.catch_clone.stores.service.inter;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.stores.dto.StoreCategoryDto;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFacilityDto;
import com.example.catch_clone.stores.dto.StoreFilesDto;
import com.example.catch_clone.stores.dto.StoreMenuDto;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.entity.StoreMenu;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface StoreService {

  StatusResponseDto addStore(StoreDto storeDto);

  StoreDto getShopInfo(Long storeId);

  StatusResponseDto addStoreMenu(Long StoreId,StoreMenuDto storeMenuDto);

  StatusResponseDto addStoresFile(Long storeId,StoreFilesDto storeFilesDto);

  StatusResponseDto addStoresFacility(Long storeId, List<StoreFacilityDto> storeFacilityDto);

  StatusResponseDto addStoresCategory(Long storeId, List<StoreCategoryDto> storeCategoryDto);

  List<StoreMenu> getMenuAllInfo(Long storeId);

  List<StoreFiles> getShopPhotoList(Long storeId);

  List<StoreDto> getShopListByCategory(Long categoryCode);


  ResponseEntity<String> getShopMapInfo(Long storeId);

}
