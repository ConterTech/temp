package com.nagoyameshi.nagoyameshi.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignupForm {
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

    @NotEmpty(message = "パスワードを入力してください。")
    @Length(min = 8, message = "パスワードは8文字以上で入力してください。")
    private String password;

    @NotEmpty(message = "パスワード（確認用）を入力してください。")
    private String passwordConfirmation;
}
