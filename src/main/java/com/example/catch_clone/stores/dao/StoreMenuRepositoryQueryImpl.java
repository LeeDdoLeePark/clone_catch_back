package com.example.catch_clone.stores.dao;

import static com.example.catch_clone.stores.entity.QStoreMenu.storeMenu;

import com.example.catch_clone.stores.entity.StoreMenu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreMenuRepositoryQueryImpl implements StoreMenuRepositoryQuery{


  private final JPAQueryFactory jpaQueryFactory;


  @Override
  @Transactional(readOnly = true)
  public List<StoreMenu> findByStoreId(Long storeId) {

    return jpaQueryFactory.select(
        storeMenu
    ).from(storeMenu)
        .where(storeMenu.store.id.eq(storeId))
        .fetch();
  }


}
