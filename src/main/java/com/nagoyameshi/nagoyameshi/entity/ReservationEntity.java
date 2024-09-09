package com.nagoyameshi.nagoyameshi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reservation")
@Data
@IdClass(value = Reservationpk.class)
public class ReservationEntity {
    @Id
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Id
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "checkin_time")
    private LocalDateTime checkinTime;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
