package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Embeddable
@Data
public class StoreBusinessTimepk {
    @Id
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Id
    @Column(name = "weekday")
    private Integer weekday;
}
