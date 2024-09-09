package com.nagoyameshi.nagoyameshi.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRegisterForm {
    @NotNull(message = "レビュー数を選択してください。")
    private Integer reviewStar;

    @NotEmpty(message = "コメントを入力してください。")
    private String reviewText;
}
