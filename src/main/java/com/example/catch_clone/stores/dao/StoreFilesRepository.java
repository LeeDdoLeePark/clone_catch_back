package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.entity.StoreFiles;
import org.springframework.data.repository.Repository;

public interface StoreFilesRepository extends Repository<StoreFiles,Long>,StoreFilesRepositoryQuery {


  void save(StoreFiles storeFiles);

//  //@Query("select sf from StoreFiles sf where sf.storeId=:storeId")
//  List<StoreFiles> findAllByStoreId(Long storeId);

}
