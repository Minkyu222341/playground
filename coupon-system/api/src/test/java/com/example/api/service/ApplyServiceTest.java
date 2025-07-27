package com.example.api.service;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.catalina.Executor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.boot.test.context.*;

import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;

/**
 * packageName    : com.example.api.service
 * fileName       : ApplyServiceTest
 * author         : MinKyu Park
 * date           : 2024-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-07        MinKyu Park       최초 생성
 */
@SpringBootTest
class ApplyServiceTest {

	@Autowired
	private ApplyService applyService;

	@Autowired
	private CouponRepository couponRepository;

	@Test
	void 한번만응모() {
		applyService.apply(1L);

		long count = couponRepository.count();

		assertThat(count).isEqualTo(1);
	}

	@Test
	void 여러명응모() throws InterruptedException {
		int threadCount = 1000;
		//ExecutorService : 병렬 작업을 간단하게 할 수 있게 도와주는 Java API
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		// CountDownLatch : 다른 Thread에서 수행하는 작업을 기다리도록 도와주는 클래스
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			long userId = i;

			executorService.submit(() -> {
				try {
					applyService.apply(userId);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		long count = couponRepository.count();

		assertThat(count).isEqualTo(100);
	}


}