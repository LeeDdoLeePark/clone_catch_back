package com.example.catch_clone.stores.service;

import com.example.catch_clone.bookmark.service.inter.BookmarkService;
import com.example.catch_clone.comment.service.inter.CommentService;
import com.example.catch_clone.reservation.service.inter.ReservationService;
import com.example.catch_clone.review.service.inter.ReviewService;
import com.example.catch_clone.stores.dao.StoreFilesRepository;
import com.example.catch_clone.stores.dao.StoreFilesRepositoryQuery;
import com.example.catch_clone.stores.dao.StoreMenuRepository;
import com.example.catch_clone.stores.dao.StoreRepository;
import com.example.catch_clone.stores.dto.StoreDto;
import com.example.catch_clone.stores.dto.StoreFilesResponseDto;
import com.example.catch_clone.stores.dto.StoreMenuResponseDto;
import com.example.catch_clone.stores.entity.Store;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.entity.StoreMenu;
import com.example.catch_clone.stores.service.inter.StoreService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public StoreDto getShopInfo(Long storeId) {

    Store store = storeRepository.findById(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게를 찾을 수 없습니다.")
    );
    return StoreDto.valueOf(store);
  }

  @Override
  public StoreMenuResponseDto getMenuAllInfo(Long storeId) {

    StoreMenu storeMenu = storeMenuRepository.findByStoreId(storeId).orElseThrow(
        ()-> new IllegalArgumentException("해당 가게의 메뉴정보를 찾을 수 없습니다.")
    );

    return new StoreMenuResponseDto(storeMenu.getStoreId(),storeMenu.getMenuNm(),storeMenu.getMenuUrl(),storeMenu.getMenuPrice(),storeMenu.getMenuMain());
  }

  @Override
  public List<StoreFiles> getShopPhotoList(Long storeId) {

    List<StoreFiles> shopPhotoList = storeFilesRepository.findAllByStoreId(storeId);
    return shopPhotoList;
  }

  @Override
  public ResponseEntity<String> getShopMapInfo(Long storeId) {
    return null;
  }
}
