package com.nagoyameshi.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.StoreSpecialBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreSpecialBusinessTimepk;

public interface StoreSpecialBusinessTimeRepository extends JpaRepository<StoreSpecialBusinessTimeEntity, StoreSpecialBusinessTimepk>{
    public List<StoreSpecialBusinessTimeEntity> findByStoreId(Integer storeId);
}
