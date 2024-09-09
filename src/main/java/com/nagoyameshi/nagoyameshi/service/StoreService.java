package com.nagoyameshi.nagoyameshi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.form.StoreEditForm;
import com.nagoyameshi.nagoyameshi.form.StoreRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    // 店舗登録
    @Transactional
    public void create(StoreRegisterForm storeRegisterForm) {
        StoreEntity store = new StoreEntity();
        MultipartFile imageFile = storeRegisterForm.getImageFile();

        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
        }

        store.setStoreName(storeRegisterForm.getStoreName());
        store.setPostCode(storeRegisterForm.getPostCode());
        store.setAddress(storeRegisterForm.getAddress());
        store.setPhoneNumber(storeRegisterForm.getPhoneNumber());
        store.setParkingStorage(storeRegisterForm.getParkingStorage());
        store.setStoreDescribe(storeRegisterForm.getStoreDescribe());
        store.setCategoryId(storeRegisterForm.getCategoryId());

        storeRepository.save(store);
    }

    // 店舗編集
    @Transactional
    public void update(StoreEditForm storeEditForm) {
        StoreEntity store = storeRepository.getReferenceById(storeEditForm.getStoreId());
        MultipartFile imageFile = storeEditForm.getImageFile();

        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
        }

        store.setStoreName(storeEditForm.getStoreName());
        store.setPostCode(storeEditForm.getPostCode());
        store.setAddress(storeEditForm.getAddress());
        store.setPhoneNumber(storeEditForm.getPhoneNumber());
        store.setParkingStorage(storeEditForm.getParkingStorage());
        store.setStoreDescribe(storeEditForm.getStoreDescribe());
        store.setCategoryId(storeEditForm.getCategoryId());

        storeRepository.save(store);
    }

    // UUIDを使って生成したファイル名を返す
    public String generateNewFileName(String fileName) {
        String[] fileNames = fileName.split("\\.");
        for (int i = 0; i < fileNames.length - 1; i++) {
            fileNames[i] = UUID.randomUUID().toString();
        }
        String hashedFileName = String.join(".", fileNames);
        return hashedFileName;
    }

    // 画像ファイルを指定したファイルにコピーする
    public void copyImageFile(MultipartFile imageFile, Path filePath) {
        try {
            Files.copy(imageFile.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
