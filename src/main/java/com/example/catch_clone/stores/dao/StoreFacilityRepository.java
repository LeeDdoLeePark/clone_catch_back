package com.example.catch_clone.stores.dao;

import com.example.catch_clone.stores.entity.StoreFacilities;
import org.springframework.data.repository.Repository;

public interface StoreFacilityRepository extends Repository<StoreFacilities,Long> {


  void save(StoreFacilities storeFacilities);
}
