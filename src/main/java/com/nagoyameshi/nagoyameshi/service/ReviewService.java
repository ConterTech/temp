package com.nagoyameshi.nagoyameshi.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagoyameshi.nagoyameshi.entity.ReviewEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.ReviewEditForm;
import com.nagoyameshi.nagoyameshi.form.ReviewRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    // レビュー作成
    @Transactional
    public void create(ReviewRegisterForm reviewRegisterForm, Integer userId, Integer storeId) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません。"));
        ReviewEntity review = new ReviewEntity();

        review.setStoreId(store);
        review.setUserId(user);
        review.setReviewStar(reviewRegisterForm.getReviewStar());
        review.setReviewText(reviewRegisterForm.getReviewText());

        reviewRepository.save(review);
    }

    // レビュー編集
    public void update(ReviewEditForm reviewEditForm, Integer userId, Integer storeId) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません。"));
        ReviewEntity review = new ReviewEntity();

        review.setStoreId(store);
        review.setUserId(user);
        review.setReviewStar(reviewEditForm.getReviewStar());
        review.setReviewText(reviewEditForm.getReviewText());

        reviewRepository.save(review);
    }
}
