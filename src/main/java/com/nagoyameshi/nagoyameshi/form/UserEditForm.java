package com.nagoyameshi.nagoyameshi.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEditForm {
    @NotNull
    private Integer userId;

    @NotEmpty(message = "氏名を入力してください。")
    private String name;

    @NotEmpty(message = "電話番号を入力してください。")
    private String phoneNumber;

    @NotEmpty(message = "郵便番号を入力してください。")
    private String postCode;

    @NotEmpty(message = "住所を入力してください。")
    private String address;

    @NotEmpty(message = "メールアドレスを入力してください。")
    private String email;

    @NotEmpty(message = "年齢を入力してください。")
    private Integer age;

    @NotEmpty(message = "性別を入力してください。")
    private String gender;
}
