package com.nagoyameshi.nagoyameshi.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "store_special_business_time")
@Data
@IdClass(value = StoreSpecialBusinessTimepk.class)
public class StoreSpecialBusinessTimeEntity {
    @Id
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Id
    @Column(name = "special_business_day")
    private LocalDate specialBusinessDay;

    @Column(name = "business_start_time")
    private LocalTime businessStartTime;

    @Column(name = "business_end_time")
    private LocalTime businessEndTime;

    @Column(name = "rest_flag")
    private boolean restFlag;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
