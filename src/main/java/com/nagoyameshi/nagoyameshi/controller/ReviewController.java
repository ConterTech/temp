package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.entity.ReviewEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.ReviewEditForm;
import com.nagoyameshi.nagoyameshi.form.ReviewRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final ReviewService reviweService;

    // レビュー一覧表示
    @GetMapping("{storeId}/review")
    public String index(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @PageableDefault(page = 0, size = 10, sort = "storeId", direction = Direction.ASC) Pageable pageable,
            Model model) {

        Page<ReviewEntity> review = reviewRepository.findByStoreId(storeId, pageable);
        StoreEntity store = storeRepository.getReferenceById(storeId);

        model.addAttribute(store);
        model.addAttribute(review);

        if (userDetailsImpl != null) {
            model.addAttribute("userId", userDetailsImpl.getUser().getUserId());
        }

        return "review/index";
    }

    // レビュー投稿画面表示
    @GetMapping("{storeId}/register")
    public String register(@PathVariable(name = "storeId") Integer storeId, Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);

        model.addAttribute(store);
        model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());

        return "review/post";
    }

    // レビュー投稿
    @PostMapping("{storeId}/create")
    public String create(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            StoreEntity store = storeRepository.getReferenceById(storeId);

            model.addAttribute(store);
            model.addAttribute(reviewRegisterForm);

            return "index/post";
        }

        UserEntity user = userDetailsImpl.getUser();
        Integer userId = user.getUserId();

        reviweService.create(reviewRegisterForm, userId, storeId);

        return "redirect:/store/{storeId}";
    }

    // レビュー編集画面表示
    @GetMapping("{storeId}/edit")
    public String edit(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        Integer userId = userDetailsImpl.getUser().getUserId();
        ReviewEntity review = reviewRepository.findByStoreIdAndUserId(userId, storeId);
        ReviewEditForm reviewEditForm = new ReviewEditForm(review.getReviewStar(), review.getReviewText());

        model.addAttribute(store);
        model.addAttribute(reviewEditForm);

        return "review/edit";
    }

    // レビュー編集
    @PostMapping("{storeId}/update")
    public String update(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            StoreEntity store = storeRepository.getReferenceById(storeId);

            model.addAttribute(store);
            model.addAttribute(reviewEditForm);

            return "review/edit";
        }

        Integer userId = userDetailsImpl.getUser().getUserId();

        reviweService.update(reviewEditForm, userId, storeId);

        return "redirect:/store/{storeId}";
    }

    // レビュー削除
    @PostMapping("{storeId}/delete")
    public String delete(@PathVariable(name = "storeId") Integer storeId, UserDetailsImpl userDetailsImpl, Model model) {
        Integer userId = userDetailsImpl.getUser().getUserId();
        ReviewEntity review = reviewRepository.findByStoreIdAndUserId(storeId, userId);
        review.setDeleteFlag(true);
        reviewRepository.save(review);
        
        return "index";
    }
}
