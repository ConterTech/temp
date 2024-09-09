package com.nagoyameshi.nagoyameshi.controller;

import java.lang.reflect.Field;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.UserEditForm;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    // ユーザ情報表示
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        UserEntity user = userRepository.getReferenceById(userDetailsImpl.getUser().getUserId());

        model.addAttribute(user);

        return "user/index";
    }

    // 編集画面表示
    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        UserEntity user = userRepository.getReferenceById(userDetailsImpl.getUser().getUserId());
        UserEditForm userEditForm = new UserEditForm(user.getUserId(), user.getName(), user.getPhoneNumber(),
                user.getPostCode(), user.getAddress(), user.getEmail(), user.getAge(), user.getGender());

        model.addAttribute(userEditForm);

        return "user/edit";
    }

    // 編集
    @PostMapping("/update")
    public String update(@ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())){
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "既に登録済みのメールアドレスです。");
            bindingResult.addError(fieldError);
        }

        if(bindingResult.hasErrors()){
            return "user/edit";
        }

        userService.update(userEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");

        return "redirect:/user";
    }
}
