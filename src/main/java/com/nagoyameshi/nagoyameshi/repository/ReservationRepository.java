package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.ReservationEntity;
import com.nagoyameshi.nagoyameshi.entity.Reservationpk;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Reservationpk>{
    public Page<ReservationEntity> findByUserId(UserEntity user, Pageable pageable);
}
