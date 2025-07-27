package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.domain.Coupon;

/**
 * packageName    : com.example.api.repository
 * fileName       : CouponRepository
 * author         : MinKyu Park
 * date           : 2024-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-07        MinKyu Park       최초 생성
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
