package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.dto.StoreCategoryDto;
import com.example.catch_clone.stores.entity.Categories;
import com.example.catch_clone.stores.entity.Store;
import java.util.List;

public interface StoreCategoryRepositoryQuery {

  List<Store> searchStoresByCategory(Long categoryCode);

}
