package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.dto.StoreFilesResponseDto;
import com.example.catch_clone.stores.entity.Store;
import com.example.catch_clone.stores.entity.StoreFiles;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface StoreFilesRepository extends Repository<StoreFiles,Long> {

  @Query("select sf from StoreFiles sf where sf.storeId=:storeId")
  List<StoreFiles> findAllByStoreId(Long storeId);

}
