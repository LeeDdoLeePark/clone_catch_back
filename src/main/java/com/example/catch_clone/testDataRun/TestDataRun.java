package com.example.catch_clone.testDataRun;

import com.example.catch_clone.stores.dao.StoreFilesRepository;
import com.example.catch_clone.stores.dao.StoreMenuRepository;
import com.example.catch_clone.stores.dao.StoreRepository;
import com.example.catch_clone.stores.entity.Store;
import com.example.catch_clone.stores.entity.StoreFiles;
import com.example.catch_clone.stores.entity.StoreMenu;
import java.awt.Menu;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataRun implements ApplicationRunner {

  private final StoreRepository storeRepository;
  private final StoreMenuRepository storeMenuRepository;

  private final StoreFilesRepository storeFilesRepository;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {



    log.info("runrunrun");
    Store store = new Store("test1","Seoul",3.0f, LocalTime.now(),"010-1234-1234","오마카세입니다.",
        LocalDateTime.now(),"가맹점공지1","N","매주 월요일","www.test.com");
    Store store2 = new Store("test2","Incheon",4.0f, LocalTime.now(),"010-7777-7777","고기집.",LocalDateTime.now(),"가맹점공지2","Y","매주 금요일","www.test22.com");
    storeRepository.save(store);
    storeRepository.save(store2);

    StoreMenu storeMenu = new StoreMenu().builder().
        storeId(store.getId())
        .menuNm("광어회")
        .menuUrl("광어회 사진 URL")
        .menuPrice("50.000")
        .menuMain("메인 메뉴: 광어 지느러미 초밥")
        .build();

    StoreMenu storeMenu2 = new StoreMenu().builder().
        storeId(store2.getId())
        .menuNm("삼겹살")
        .menuUrl("삽겹살 사진 URL")
        .menuPrice("20.000")
        .menuMain("메인 메뉴: 흑돼지")
        .build();

    storeMenuRepository.save(storeMenu);
    storeMenuRepository.save(storeMenu2);

    StoreFiles storeFiles = new StoreFiles().builder()
        .storeId(1L)
        .fileUrl("test1 url1")
        .createdAt(LocalDateTime.now())
        .build();

    StoreFiles storeFiles2 = new StoreFiles().builder()
        .storeId(1L)
        .fileUrl("test1 url2")
        .createdAt(LocalDateTime.now())
        .build();


    StoreFiles storeFiles3 = new StoreFiles().builder()
        .storeId(2L)
        .fileUrl("test2 url1")
        .createdAt(LocalDateTime.now())
        .build();

    StoreFiles storeFiles4 = new StoreFiles().builder()
        .storeId(1L)
        .fileUrl("test2 url2")
        .createdAt(LocalDateTime.now())
        .build();


    storeFilesRepository.save(storeFiles);
    storeFilesRepository.save(storeFiles2);
    storeFilesRepository.save(storeFiles3);
    storeFilesRepository.save(storeFiles4);




  }
}
