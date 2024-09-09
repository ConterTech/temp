package com.nagoyameshi.nagoyameshi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.FavoriteEntity;
import com.nagoyameshi.nagoyameshi.entity.Favoritepk;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Favoritepk> {
    public Page<FavoriteEntity> findByStoreId(StoreEntity StoreId, Pageable Pageable);
    public Optional<FavoriteEntity> findByStoreIdAndUserId(Integer storeId, UserEntity userId);
    public List<FavoriteEntity> findByUserId(UserEntity userId);
    public FavoriteEntity deleteByUserIdAndStoreId(UserEntity userId, StoreEntity storeId);
}
