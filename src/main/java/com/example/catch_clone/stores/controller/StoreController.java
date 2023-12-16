package com.example.catch_clone.stores.controller;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.stores.dto.StoreCategoryDto;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFacilityDto;
import com.example.catch_clone.stores.dto.StoreFilesDto;
import com.example.catch_clone.stores.dto.StoreMenuDto;
import com.example.catch_clone.stores.entity.StoreFacilities;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.entity.StoreMenu;
import com.example.catch_clone.stores.service.inter.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/shop")
public class StoreController{

  private final StoreService storeService;

//가맹점 정보 가져오기 (단건)
  @GetMapping
  public ResponseEntity<StoreDto> getStoreInfo(@RequestParam Long storeId ){
    StoreDto storeDto = storeService.getShopInfo(storeId);
    return ResponseEntity.status(HttpStatus.OK).body(storeDto);
  }

  //가맹점 메뉴 전부 조회하기
  @GetMapping("/menuAllList")
  public ResponseEntity<List<StoreMenu>> getMenuAllInfo(@RequestParam Long storeId){

    List<StoreMenu> storeMenuList= storeService.getMenuAllInfo(storeId);
    return ResponseEntity.status(HttpStatus.OK).body(storeMenuList);
  }

  //가맹점 정보(사진들) 가져오기
  @GetMapping("/shopPhotoList")
  public ResponseEntity<List<StoreFiles>> getShopPhotoList(@RequestParam Long storeId){

    List<StoreFiles> storeFiles = storeService.getShopPhotoList(storeId);
    return ResponseEntity.status(HttpStatus.OK).body(storeFiles);
  }

  //(테스트용) 가맹점 등록하기
  @PostMapping("/addShopInfo")
  public ResponseEntity<StatusResponseDto> addStore(@RequestBody StoreDto storeDto){

    return ResponseEntity.ok().body(storeService.addStore(storeDto));
  }

  //(테스트용) 가맹점 메뉴 등록하기
  @PostMapping("/{storeId}/addMenuInfo")
  public ResponseEntity<StatusResponseDto> addStoreMenu(@PathVariable Long storeId,@RequestBody StoreMenuDto storeMenuDto){

    return ResponseEntity.ok().body(storeService.addStoreMenu(storeId,storeMenuDto));
  }

  //(테스트용) 가맹점 사진 등록하기
  //아마 사진도 한번에 다중으로 등록할 수 있어야겠지만, 일단 단건 등록부터 구현
  @PostMapping("/{storeId}/addStoreFiles")
  public ResponseEntity<StatusResponseDto> addStoreMenu(@PathVariable Long storeId,@RequestBody StoreFilesDto storeFilesDto){

    return ResponseEntity.ok().body(storeService.addStoresFile(storeId,storeFilesDto));
  }

  //(테스트용) 가맹점 카테고리 등록하기
  //아마 한번에 다중으로 저장할 수 있게 구현해야할 것임.
  @PostMapping("/{storeId}/addStoreCategory")
  public ResponseEntity<StatusResponseDto> addStoreCategory(@PathVariable Long storeId,@RequestBody List<StoreCategoryDto> storeCategoryDto){

    return ResponseEntity.ok().body(storeService.addStoresCategory(storeId,storeCategoryDto));
  }

  //카테고리로 가맹점 조회하기
  //아마 카테고리는 더욱 다양하고 다중의 카테고리가 필요할 수 있겠지만, 일단 단건의 카테고리로 검색
  @GetMapping("/searchByCate")
  public List<StoreDto> getStoresByCategory(@RequestParam Long categoryCode){
   return storeService.getShopListByCategory(categoryCode);
  }


 // (테스트용) 가맹점 편의시설 등록하기
  //아마 한번에 다중으로 저장할 수 있게 구현해야할 것임.
  @PostMapping("/{storeId}/addStoreFacility")
  public ResponseEntity<StatusResponseDto> addStoreFacility(@PathVariable Long storeId,@RequestBody List<StoreFacilityDto> storeFacilityDto){

    return ResponseEntity.ok().body(storeService.addStoresFacility(storeId,storeFacilityDto));
  }

  //따로 위치만 가져오는게 필요가 있을까?
  //가게 정보에 들어있는데
  @GetMapping("/map")
  public ResponseEntity<String> getShopMapInfo(@RequestParam Long storeId){
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }


}
