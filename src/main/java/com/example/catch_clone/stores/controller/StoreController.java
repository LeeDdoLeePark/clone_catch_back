package com.example.catch_clone.stores.controller;

import com.example.catch_clone.security.dto.StatusResponseDto;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFilesDto;
import com.example.catch_clone.stores.dto.StoreMenuDto;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.service.inter.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<StoreMenuDto> getMenuAllInfo(@RequestParam Long storeId){

    StoreMenuDto storeMenuDto = storeService.getMenuAllInfo(storeId);
    return ResponseEntity.status(HttpStatus.OK).body(storeMenuDto);
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
  @PostMapping("/addMenuInfo")
  public ResponseEntity<StatusResponseDto> addStoreMenu(@RequestBody StoreMenuDto storeMenuDto){

    return ResponseEntity.ok().body(storeService.addStoreMenu(storeMenuDto));
  }

  //(테스트용) 가맹점 사진 등록하기
  @PostMapping("/addStoreFiles")
  public ResponseEntity<StatusResponseDto> addStoreMenu(@RequestBody StoreFilesDto storeFilesDto){

    return ResponseEntity.ok().body(storeService.addStoresFile(storeFilesDto));
  }

  //따로 위치만 가져오는게 필요가 있을까?
  //가게 정보에 들어있는데
  @GetMapping("/map")
  public ResponseEntity<String> getShopMapInfo(@RequestParam Long storeId){
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }


}
