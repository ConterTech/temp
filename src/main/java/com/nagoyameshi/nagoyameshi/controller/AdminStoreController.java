package com.nagoyameshi.nagoyameshi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.form.StoreEditForm;
import com.nagoyameshi.nagoyameshi.form.StoreRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.service.StoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/stores")
public class AdminStoreController {
    private final StoreRepository storeRepository;
    private final StoreService storeService;

    // 管理者店舗一覧表示
    @GetMapping
    public String indexAdmin(Model model,
            @PageableDefault(page = 0, size = 10, sort = "storeId", direction = Direction.ASC) Pageable pageable,
            @RequestParam(name = "keyword", required = false) String keyword) {

        Page<StoreEntity> storePage;

        if (StringUtils.isEmpty(keyword)) {
            storePage = storeRepository.findAll(pageable);
        } else {
            storePage = storeRepository.findByStoreNameLike(keyword + "%", pageable);
        }

        model.addAttribute("storePage", storePage);
        model.addAttribute("keyword", keyword);

        return "index";
    }

    // 管理者店舗登録画面表示
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("storeRegisterForm", new StoreRegisterForm());

        return "index";
    }

    // 管理者店舗登録
    @PostMapping("/create")
    public String create(@ModelAttribute @Validated StoreRegisterForm storeRegisterForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "index";
        }

        storeService.create(storeRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");

        return "index";
    }

    // 管理者店舗編集画面
    @GetMapping("/{storeId}/edit")
    public String edit(@PathVariable(name = "storeId") Integer storeId, Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        String imageName = store.getImageName();
        StoreEditForm storeEditForm = new StoreEditForm(store.getStoreId(), store.getStoreName(), null,
                store.getPostCode(), store.getAddress(), store.getPhoneNumber(), store.getParkingStorage(),
                store.getStoreDescribe(), store.getCategoryId());

        model.addAttribute("imageName", imageName);
        model.addAttribute("storeEditForm", storeEditForm);

        return "index";
    }

    // 管理者店舗編集
    @PostMapping("/{storeId}/update")
    public String update(@ModelAttribute @Validated StoreEditForm storeEditForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        storeService.update(storeEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");

        return "index";
    }

    // 管理者店舗削除
    @GetMapping("/{storeId}/delete")
    public String delete(Model model, @PathVariable(name = "storeId") Integer storeId) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        store.setDeleteFlag(true);
        storeRepository.save(store);

        return "index";
    }
}
