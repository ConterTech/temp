package com.nagoyameshi.nagoyameshi.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.entity.StoreBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreSpecialBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.ReservationInputForm;
import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;
import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreBusinessTimeRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreSpecialBusinessTimeRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreRepository storeRepository;
    private final StoreBusinessTimeRepository storeBusinessTimeRepository;
    private final StoreSpecialBusinessTimeRepository storeSpecialBusinessTimeRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;
    private final FavoriteService favoriteService;

    // 店舗詳細ページ
    @GetMapping("/{storeId}")
    public String show(@PathVariable(name = "storeId") Integer storeId, Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        List<StoreBusinessTimeEntity> storeBusinessTime = storeBusinessTimeRepository.findByStoreId(storeId);
        List<StoreSpecialBusinessTimeEntity> storeSpecialBusinessTime = storeSpecialBusinessTimeRepository
                .findByStoreId(storeId);

        model.addAttribute("store", store);
        model.addAttribute("storeBusinessTime", storeBusinessTime);
        model.addAttribute("storeSpecialBusinessTime", storeSpecialBusinessTime);
        model.addAttribute("reservationForm", new ReservationInputForm());

        return "index";
    }

    // お気に入り追加
    @PostMapping("/{storeId}/addFavorite")
    public String addFavorite(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        Integer userId = userDetailsImpl.getUser().getUserId();

        favoriteService.add(userId, storeId);

        return "redirect:/store/{storeId}";
    }

    // お気に入り削除
    @PostMapping("/{storeId}/deleteFavorite")
    public String deleteFavorite(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        UserEntity user = userDetailsImpl.getUser();
        StoreEntity store = storeRepository.getReferenceById(storeId);

        favoriteRepository.deleteByUserIdAndStoreId(user, store);

        return "redirect:/store/{storeId}";
    }
}
