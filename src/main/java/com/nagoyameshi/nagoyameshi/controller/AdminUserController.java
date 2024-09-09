package com.nagoyameshi.nagoyameshi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserRepository userRepository;

    // 管理者ユーザ情報一覧表示
    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Direction.ASC) Pageable pageable,
            Model model) {

        Page<UserEntity> userPage;

        if (StringUtils.isEmpty(keyword)) {
            userPage = userRepository.findAll(pageable);
        } else {
            userPage = userRepository.findByEmail(keyword, pageable);
        }

        model.addAttribute(userPage);
        model.addAttribute(keyword);

        return "admin/user/index";
    }

    // 管理者ユーザ情報詳細表示
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "userId")Integer userId, Model model) {
        UserEntity user = userRepository.getReferenceById(userId);

        model.addAttribute(user);

        return "admin/user/show";
    }
}
