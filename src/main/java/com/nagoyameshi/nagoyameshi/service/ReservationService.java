package com.nagoyameshi.nagoyameshi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagoyameshi.nagoyameshi.entity.ReservationEntity;
import com.nagoyameshi.nagoyameshi.form.ReservationRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.ReservationRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(ReservationRegisterForm reservationRegisterForm){
        ReservationEntity reservation = new ReservationEntity();
        
        LocalDateTime checkinTime = LocalDateTime.parse(reservationRegisterForm.getCheckinTime());

        reservation.setStoreId(storeRepository.getReferenceById(reservationRegisterForm.getStoreId()));
        reservation.setUserId(userRepository.getReferenceById(reservationRegisterForm.getUserId()));
        reservation.setCheckinTime(checkinTime);
        reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
        reservation.setRemarks(reservationRegisterForm.getRemarks());
        reservation.setDeleteFlag(false);

        reservationRepository.save(reservation);
    }

    // 予約人数が定員以下かどうかチェックする
    public boolean isWithnCapacity(Integer numberOfPeople, Integer capacity){
        return numberOfPeople <= capacity;
    }
}
