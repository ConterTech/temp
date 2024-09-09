package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer>{
    public VerificationToken findByToken(String token);
}
