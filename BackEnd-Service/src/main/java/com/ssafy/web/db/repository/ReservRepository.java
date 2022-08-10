package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.web.db.entity.Reservation;

public interface ReservRepository extends JpaRepository<Reservation, Integer> {

	/** 상담사한테 예약된 상담 조회 */
	@Query(value = "select * from reservation where thera_id :thera_id", nativeQuery = true)
	public List<Reservation> selectAllSQL(@Param(value = "thera_id") String thera_id);

}
