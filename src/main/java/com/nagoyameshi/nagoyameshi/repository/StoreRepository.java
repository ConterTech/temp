package com.nagoyameshi.nagoyameshi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{
    public Page<StoreEntity> findByStoreNameLike(String keyword, Pageable pageable);
    public Page<StoreEntity> findByCategoryId(Integer categoryId, Pageable pageable);
    public Optional<StoreEntity> findByStoreId(Integer storeId);

    public Page<StoreEntity> findByStoreIdIn(List<StoreEntity> storeList, Pageable pageable);
}
