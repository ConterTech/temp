package com.nagoyameshi.nagoyameshi.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagoyameshi.nagoyameshi.entity.FavoriteEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final StoreRepository storeRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    // 追加
    @Transactional
    public void add(Integer userId, Integer storeId){
        StoreEntity store = storeRepository.getReferenceById(storeId);
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりませんでした。"));
        FavoriteEntity favorite = new FavoriteEntity();

        favorite.setStoreId(store);
        favorite.setUserId(user);

        favoriteRepository.save(favorite);
    }
}
