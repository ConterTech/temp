package com.nagoyameshi.nagoyameshi.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class Favoritepk {
    @Id
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;
}
