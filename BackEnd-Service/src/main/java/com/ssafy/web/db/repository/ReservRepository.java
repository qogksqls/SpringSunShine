package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Reservation;

public interface ReservRepository extends JpaRepository<Reservation, Integer> {

	/** 상담사한테 예약된 상담 조회 */
	List<Reservation> findByTheraId(String theraId);

	/** 보호자가 예약한 상담 조회 */
	List<Reservation> findByParentId(String parentId);

	/** 보호자 아이디와 아동 아이디로 예약한 상담 조회 */
	List<Reservation> findByParentIdAndChildId(String parentId, String childId);

}
