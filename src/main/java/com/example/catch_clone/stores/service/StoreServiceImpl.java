package com.example.catch_clone.stores.service;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.stores.dao.StoreCategoryRepository;
import com.example.catch_clone.stores.dao.StoreFacilityRepository;
import com.example.catch_clone.stores.dao.StoreFilesRepository;
import com.example.catch_clone.stores.dao.StoreMenuRepository;
import com.example.catch_clone.stores.dao.StoreRepository;
import com.example.catch_clone.stores.dto.StoreCategoryDto;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFacilityDto;
import com.example.catch_clone.stores.dto.StoreFilesDto;
import com.example.catch_clone.stores.dto.StoreMenuDto;
import com.example.catch_clone.stores.entity.Categories;
import com.example.catch_clone.stores.entity.Store;
import com.example.catch_clone.stores.entity.StoreFacilities;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.entity.StoreMenu;
import com.example.catch_clone.stores.service.inter.StoreService;
import java.util.ArrayList;
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

  private final StoreCategoryRepository storeCategoryRepository;

  private final StoreFacilityRepository storeFacilityRepository;


  @Override
  @Transactional
  public StatusResponseDto addStore(StoreDto storeDto) {


    storeRepository.save(new Store(storeDto));
    StatusResponseDto statusResponseDto = new StatusResponseDto(201,"Created");
    return statusResponseDto;
  }


  @Override
  public StatusResponseDto addStoresFile(Long storeId,StoreFilesDto storeFilesDto) {

    Store store = storeRepository.findById(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게를 찾을 수 없습니다.")
    );
    storeFilesRepository.save(new StoreFiles(store,storeFilesDto));
    return new StatusResponseDto(201,"Created");
  }

  @Override
  @Transactional
  public StatusResponseDto addStoreMenu(Long storeId,StoreMenuDto storeMenuDto) {
    Store store = storeRepository.findById(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게를 찾을 수 없습니다.")
    );
    storeMenuRepository.save(new StoreMenu(store,storeMenuDto));
    return new StatusResponseDto(201,"Created");
  }

  @Override
  public List<StoreDto> getShopListByCategory(Long categoryCode) {

    List<Store> stores = storeCategoryRepository.searchStoresByCategory(categoryCode);

    //return stores.forEach(s->StoreDto.valueOf(s)); 왜 반환이 void일까...

    List<StoreDto> storeDtos = new ArrayList<>();

    for(Store store : stores){
      storeDtos.add(StoreDto.valueOf(store));
    }

    return storeDtos;


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
  public List<StoreMenu> getMenuAllInfo(Long storeId) {

    List<StoreMenu> storeMenu = storeMenuRepository.findByStoreId(storeId);

    return storeMenu;
  }

  @Override
  public StatusResponseDto addStoresCategory(Long storeId, List<StoreCategoryDto> storeCategoryDto) {

    Store store = storeRepository.findById(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게를 찾을 수 없습니다.")
    );

    for(StoreCategoryDto storeCategoryDto1: storeCategoryDto ){

         storeCategoryRepository.save(new Categories(store,storeCategoryDto1));
    }


    return new StatusResponseDto(201,"Created");
  }

  @Override
  public List<StoreFiles> getShopPhotoList(Long storeId) {


    List<StoreFiles> shopPhotoList = storeFilesRepository.findAllByStoreId(storeId);
    return shopPhotoList;
  }

  @Override
  public StatusResponseDto addStoresFacility(Long storeId,
      List<StoreFacilityDto> storeFacilityDto) {


    Store store = storeRepository.findById(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게를 찾을 수 없습니다.")
    );

    for(StoreFacilityDto storeFacilityDto1 : storeFacilityDto){

      storeFacilityRepository.save(new StoreFacilities().builder()
              .store(store)
              .storeFacilityDto(storeFacilityDto1)
          .build());
    }
    return new StatusResponseDto(201,"Created");
  }

  @Override
  public ResponseEntity<String> getShopMapInfo(Long storeId) {
    return null;
  }
}
