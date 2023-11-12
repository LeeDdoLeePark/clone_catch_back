package com.example.catch_clone.stores.controller;

import com.example.catch_clone.stores.dto.StoreFilesResponseDto;
import com.example.catch_clone.stores.dto.StoreMenuResponseDto;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.service.inter.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ct/shop")
public class StoreController{

  private final StoreService storeService;


  @GetMapping
  public ResponseEntity<StoreDto> getStoreInfo(@RequestParam Long storeId ){
    StoreDto storeDto = storeService.getShopInfo(storeId);
    return ResponseEntity.status(HttpStatus.OK).body(storeDto);
  }

  @GetMapping("/menuAllList")
  public ResponseEntity<StoreMenuResponseDto> getMenuAllInfo(@RequestParam Long storeId){

    StoreMenuResponseDto storeMenuResponseDto = storeService.getMenuAllInfo(storeId);
    return ResponseEntity.status(HttpStatus.OK).body(storeMenuResponseDto);
  }

  @GetMapping("/shopPhotoList")
  public ResponseEntity<List<StoreFiles>> getShopPhotoList(@RequestParam Long storeId){

    List<StoreFiles> storeFiles = storeService.getShopPhotoList(storeId);
    return ResponseEntity.status(HttpStatus.OK).body(storeFiles);
  }

  //따로 위치만 가져오는게 필요가 있을까?
  //가게 정보에 들어있는데
  @GetMapping("/map")
  public ResponseEntity<String> getShopMapInfo(@RequestParam Long storeId){
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }


}
