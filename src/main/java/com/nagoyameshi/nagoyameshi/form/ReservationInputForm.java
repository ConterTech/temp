package com.nagoyameshi.nagoyameshi.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
    @NotEmpty(message = "開始時間を入力してください。")
    private LocalDateTime checkinTime;

    @NotNull(message = "ご来店人数を入力してください。")
    @Min(value = 1, message = "人数は1人以上に設定してください。")
    private Integer numberOfPeople;

    private String remarks;

    @NotEmpty(message = "ストアIDが存在しません。")
    private Integer storeId;
}
