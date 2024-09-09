package com.nagoyameshi.nagoyameshi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final StoreRepository storeRepository;

    // homeページ
    @GetMapping
    public String index(@RequestParam(name = "store", required = false) String store,
            @RequestParam(name = "category", required = false) Integer category,
            @PageableDefault(page = 0, size = 10, sort = "storeId", direction = Direction.ASC) Pageable pageable,
            Model model) {

        Page<StoreEntity> storePage;

        if (StringUtils.isNotEmpty(store)) {
            storePage = storeRepository.findByStoreNameLike(store + "%", pageable);
        } else if (category != null) {
            storePage = storeRepository.findByCategoryId(category, pageable);
        } else {
            storePage = storeRepository.findAll(pageable);
        }

        model.addAttribute(storePage);
        model.addAttribute(store);
        model.addAttribute(category);

        return "index";
    }
}
