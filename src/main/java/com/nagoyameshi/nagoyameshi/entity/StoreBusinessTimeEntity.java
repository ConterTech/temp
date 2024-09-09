package com.nagoyameshi.nagoyameshi.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "store_business_time")
@Data
@IdClass(value = StoreBusinessTimepk.class)
public class StoreBusinessTimeEntity {
    @Id
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Id
    @Column(name = "weekday")
    private Integer weekday;

    @Column(name = "business_start_time")
    private LocalTime businessStartTime;

    @Column(name = "business_end_time")
    private LocalTime businessEndTime;

    @Column(name = "rest_flag")
    private boolean restFlag;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
