package com.example.catch_clone.stores.dao;

import static com.example.catch_clone.stores.entity.QCategories.categories;

import com.example.catch_clone.stores.entity.Categories;
import com.example.catch_clone.stores.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
public class StoreCategoryRepositoryQueryImpl implements StoreCategoryRepositoryQuery {


  JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public List<Store> searchStoresByCategory(Long categoryCode) {

    //이부분의 쿼리를 잘못짰을수도..
    return jpaQueryFactory.select(
            categories.store
    ).from(categories)
        .where(categories.categoryCode.eq(categoryCode))
        .fetch();
  }
}
