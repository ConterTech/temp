package com.nagoyameshi.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.StoreBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreBusinessTimepk;

public interface StoreBusinessTimeRepository extends JpaRepository<StoreBusinessTimeEntity, StoreBusinessTimepk>{
    public List<StoreBusinessTimeEntity> findByStoreId(Integer storeId);
}
