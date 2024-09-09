package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Embeddable
@Data
public class Reservationpk {
    @Id
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Id
    @JoinColumn(name = "user_id")
    private UserEntity userId;
}
