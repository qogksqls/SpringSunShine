package com.ssafy.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.web.model.response.ConsultResponse;
import com.ssafy.web.model.response.ConsultTotalResponse;
import com.ssafy.web.request.ConsultRequest;

public interface ConsultService {
	int createRoom(ConsultRequest conreq);
	void updateMemo(ConsultRequest conreq);
	void updateRecord(ConsultRequest conreq);
	ConsultResponse findByConsultNo(int ConsultNo);
	List<ConsultTotalResponse> findByTheraIdAndChildId(String theraId, String childid, Pageable pageable);
	List<ConsultTotalResponse> findByTheraId(String theraId, Pageable pageable);
	List<ConsultTotalResponse> findByParentIdAndChildId(String parentId, String childId, Pageable pageable);
	List<ConsultTotalResponse> findByParentId(String parentId, Pageable pageable);
}
