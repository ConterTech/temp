package com.nagoyameshi.nagoyameshi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagoyameshi.nagoyameshi.entity.RoleEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.SignupForm;
import com.nagoyameshi.nagoyameshi.form.UserEditForm;
import com.nagoyameshi.nagoyameshi.repository.RoleRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // ユーザ登録
    @Transactional
    public UserEntity create(SignupForm signupForm) {
        UserEntity user = new UserEntity();
        RoleEntity role = roleRepository.findByName("ROLE_GENERAL");

        user.setName(signupForm.getName());
        user.setPhoneNumber(signupForm.getPhoneNumber());
        user.setPostCode(signupForm.getPostCode());
        user.setAddress(signupForm.getAddress());
        user.setEmail(signupForm.getEmail());
        user.setAge(signupForm.getAge());
        user.setGender(signupForm.getGender());
        user.setPasword(passwordEncoder.encode(signupForm.getPassword()));
        user.setRole(role);
        user.setEnabled(true);

        return userRepository.save(user);
    }

    // ユーザ編集
    @Transactional
    public void update(UserEditForm userEditForm){
        UserEntity user = userRepository.getReferenceById(userEditForm.getUserId());

        user.setName(null);
        
    }

    // メールアドレスが登録済みかどうかチェックする
    public boolean isEmailRegistered(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null;
    }

    // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    // ユーザを有効にする
    @Transactional
    public void enableUser(UserEntity user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    // メールアドレスが変更されたかどうかをチェックする
    public boolean isEmailChanged(UserEditForm userEditForm) {
        UserEntity currentUser = userRepository.getReferenceById(userEditForm.getUserId());
        return !userEditForm.getEmail().equals(currentUser.getEmail());
    }
}
