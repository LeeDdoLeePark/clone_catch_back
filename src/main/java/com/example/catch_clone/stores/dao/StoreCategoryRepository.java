package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.entity.Categories;
import org.springframework.data.repository.Repository;

public interface StoreCategoryRepository extends Repository<Categories,Long>,StoreCategoryRepositoryQuery {

  void save(Categories categories);

}
