package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Reservation;

public interface ReservRepository extends JpaRepository<Reservation, Integer> {

	/** 상담사한테 예약된 상담 조회 */
	List<Reservation> findByTheraId(String theraId);

}
