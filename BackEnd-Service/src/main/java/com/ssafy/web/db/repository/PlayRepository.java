package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.web.db.entity.play.Play;

public interface PlayRepository extends JpaRepository<Play, Integer> {

	@Query(value = "select * from play where child_id = :childId " + "order by create_time asc", nativeQuery = true)
	List<Play> findByChildId(@Param("childId") String childId);

}
