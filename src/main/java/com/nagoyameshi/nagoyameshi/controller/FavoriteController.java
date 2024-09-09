package com.nagoyameshi.nagoyameshi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.entity.FavoriteEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;
    private final StoreRepository storeRepository;

    // お気に入り一覧表示
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @PageableDefault(page = 0, size = 10, sort = "storeId", direction = Direction.ASC) Pageable pageable,
            Model model) {

        UserEntity user = userDetailsImpl.getUser();

        List<FavoriteEntity> favorites = favoriteRepository.findByUserId(user);
        List<StoreEntity> storeIdList = new ArrayList<>();

        for (FavoriteEntity favorite : favorites) {
            StoreEntity storeId = favorite.getStoreId();
            storeIdList.add(storeId);
        }

        Page<StoreEntity> storePage = storeRepository.findByStoreIdIn(storeIdList, pageable);

        model.addAttribute(storePage);

        return "index";
    }
}
