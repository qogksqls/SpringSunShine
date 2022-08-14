package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.web.db.entity.Consult;
import com.ssafy.web.model.response.ConsultResponse;

public interface ConsultRepository extends JpaRepository<Consult , Integer>{
	Consult findByConsultNo(int ConsultNo);
	
	@Query(value="select new com.ssafy.web.model.response.ConsultResponse"
			+ "(c.consultNo as consultNo, c.startedTime as startedTime, "
			+ "c.closedTime as closedTime, c.theraId as theraId, "
			+ "c.childId as childId, c.parentId as parentId, "
			+ "c.memo as memo, c.record as record)"
			+ "from Consult c "
			+ "where c.theraId = :theraId "
			+ "and c.childId = :childId")
	List<ConsultResponse> findByTheraIdAndChildIdjpql(
			@Param("theraId") String theraId,@Param("childId") String childId,
			Pageable pageable);
	
	@Query(value="select new com.ssafy.web.model.response.ConsultResponse"
			+ "(c.consultNo as consultNo, c.startedTime as startedTime, "
			+ "c.closedTime as closedTime, c.theraId as theraId, "
			+ "c.childId as childId, c.parentId as parentId, "
			+ "c.memo as memo, c.record as record)"
			+ "from Consult c "
			+ "where c.theraId = :theraId")
	List<ConsultResponse> findByTheraIdjpql(
			@Param("theraId") String theraId,
			Pageable pageable);
	
	
	@Query(value="select new com.ssafy.web.model.response.ConsultResponse"
			+ "(c.consultNo as consultNo, c.startedTime as startedTime, "
			+ "c.closedTime as closedTime, c.theraId as theraId, "
			+ "c.childId as childId, c.parentId as parentId, "
			+ "c.memo as memo, c.record as record)"
			+ "from Consult c "
			+ "where c.childId = :childId and c.parentId = :parentId")
	List<ConsultResponse> findByParentIdAndChildIdjpql(
			@Param("parentId") String parentId, @Param("childId") String childId, 
			Pageable pageable);
	
	@Query(value="select new com.ssafy.web.model.response.ConsultResponse"
			+ "(c.consultNo as consultNo, c.startedTime as startedTime, "
			+ "c.closedTime as closedTime, c.theraId as theraId, "
			+ "c.childId as childId, c.parentId as parentId, "
			+ "c.memo as memo, c.record as record)"
			+ "from Consult c "
			+ "where c.parentId = :parentId")
	List<ConsultResponse> findByParentIdjpql(
			@Param("parentId") String parentId,
			Pageable pageable);
	
	
}