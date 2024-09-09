package com.nagoyameshi.nagoyameshi.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
    private Integer storeId;

    private Integer userId;

    private String checkinTime;

    private Integer numberOfPeople;

    private String remarks;
}
