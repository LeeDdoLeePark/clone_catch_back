package com.example.catch_clone.stores.service;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.stores.dao.StoreFilesRepository;
import com.example.catch_clone.stores.dao.StoreMenuRepository;
import com.example.catch_clone.stores.dao.StoreRepository;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFilesDto;
import com.example.catch_clone.stores.dto.StoreMenuDto;
import com.example.catch_clone.stores.entity.Store;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.entity.StoreMenu;
import com.example.catch_clone.stores.service.inter.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {


  private final StoreRepository storeRepository;
  private final StoreMenuRepository storeMenuRepository;
  private final StoreFilesRepository storeFilesRepository;


  @Override
  @Transactional
  public StatusResponseDto addStore(StoreDto storeDto) {


    storeRepository.save(new Store(storeDto));
    StatusResponseDto statusResponseDto = new StatusResponseDto(201,"Created");
    return statusResponseDto;
  }


  @Override
  public StatusResponseDto addStoresFile(StoreFilesDto storeFilesDto) {

    storeFilesRepository.save(new StoreFiles(storeFilesDto));
    return new StatusResponseDto(201,"Created");
  }

  @Override
  @Transactional
  public StatusResponseDto addStoreMenu(StoreMenuDto storeMenuDto) {
    storeMenuRepository.save(new StoreMenu(storeMenuDto));
    return new StatusResponseDto(201,"Created");
  }

  @Override
  @Transactional
  public StoreDto getShopInfo(Long storeId) {

    Store store = storeRepository.findById(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게를 찾을 수 없습니다.")
    );
    return StoreDto.valueOf(store);
  }

  @Override
  public StoreMenuDto getMenuAllInfo(Long storeId) {

    StoreMenu storeMenu = storeMenuRepository.findByStoreId(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게의 메뉴정보를 찾을 수 없습니다.")
    );

    return new StoreMenuDto(storeMenu.getStoreId(),storeMenu.getMenuNm(),storeMenu.getMenuUrl(),storeMenu.getMenuPrice(),storeMenu.getMenuMain());
  }

  @Override
  public List<StoreFiles> getShopPhotoList(Long storeId) {

    List<StoreFiles> shopPhotoList = storeFilesRepository.findAllByStoreId(storeId);
    return shopPhotoList;
  }

  @Override
  @Transactional
  public Store findById(Long storeId) {
    return storeRepository.findById(storeId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다.")
    );
  }
  @Override
  public ResponseEntity<String> getShopMapInfo(Long storeId) {
    return null;
  }
}
