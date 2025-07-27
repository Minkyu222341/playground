package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.example.api.service
 * fileName       : ApplyService
 * author         : MinKyu Park
 * date           : 2024-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-07        MinKyu Park       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ApplyService {

	private final CouponRepository couponRepository;
	private final CouponCountRepository couponCountRepository;

	public void apply(Long userId) {
		long count = couponCountRepository.increment();

		if (count > 100) {
			return;
		}

		couponRepository.save(new Coupon(userId));
	}

}
