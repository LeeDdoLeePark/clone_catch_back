package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.entity.StoreMenu;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface StoreMenuRepository extends Repository<StoreMenu,Long>,StoreMenuRepositoryQuery {


  void save(StoreMenu storeMenu);


}
