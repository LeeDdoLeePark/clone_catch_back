package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.entity.StoreMenu;
import java.util.List;
import java.util.Optional;

public interface StoreMenuRepositoryQuery {

  List<StoreMenu> findByStoreId(Long storeId);
}
