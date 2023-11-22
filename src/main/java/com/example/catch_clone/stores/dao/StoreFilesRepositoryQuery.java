package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.entity.StoreFiles;
import java.util.List;

public interface StoreFilesRepositoryQuery {

  //@Query("select sf from StoreFiles sf where sf.storeId=:storeId")
  List<StoreFiles> findAllByStoreId(Long storeId);


}
