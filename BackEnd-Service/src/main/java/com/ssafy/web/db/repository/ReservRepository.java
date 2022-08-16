package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Reservation;

public interface ReservRepository extends JpaRepository<Reservation, Integer> {

	/** 상담사한테 예약된 상담 조회 */
	@Query(value = "select * from reservation where reserv_time > now() "
			+ "and thera_id = :theraId "
			+ "order by reserv_time asc", nativeQuery = true)
	List<Reservation> findByTheraId(@Param("theraId") String theraId);

	/** 상담사 + 아동 아이디로 예약된 상담 조회 */
	@Query(value = "select * from reservation where reserv_time > now() "
			+ "and thera_id = :theraId and child_id = :childId "
			+ "order by reserv_time asc", nativeQuery = true)
	List<Reservation> findByTheraIdAndChildId(@Param("theraId") String theraId, @Param("childId") String childId);

	/** 보호자가 예약한 상담 조회 */
	@Query(value = "select * from reservation where reserv_time > now() "
			+ "and parent_id = :parentId "
			+ "order by reserv_time asc", nativeQuery = true)
	List<Reservation> findByParentId(@Param("parentId") String parentId);

	/** 보호자 아이디와 아동 아이디로 예약한 상담 조회 */
	@Query(value = "select * from reservation where reserv_time > now() "
			+ "and parent_id = :parentId and child_id = :childId "
			+ "order by reserv_time asc", nativeQuery = true)
	List<Reservation> findByParentIdAndChildId(@Param("parentId") String parentId, @Param("childId") String childId);

}
