package com.example.catch_clone.stores.dao;

import static com.example.catch_clone.stores.entity.QStoreFiles.storeFiles;

import com.example.catch_clone.stores.entity.StoreFiles;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreFilesRepositoryQueryImpl implements StoreFilesRepositoryQuery{

  private final JPAQueryFactory queryFactory;

  @Override
  @Transactional(readOnly = true)
  public List<StoreFiles> findAllByStoreId(Long storeId) {

    return queryFactory.select(
       storeFiles
    ).from(storeFiles).
        where(storeFiles.storeId.eq(storeId))
        .fetch();


  }
}
