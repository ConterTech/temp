package com.nagoyameshi.nagoyameshi.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewEditForm {
    @NotNull(message = "レビュー数を選択してください。")
    private Integer reviewStar;

    @NotEmpty(message = "コメントを入力してください")
    private String reviewText;
}
