package com.ssafy.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.web.dto.ChildData;
import com.ssafy.web.model.response.ChildReservResponse;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;

public interface ChildService {

	// 아동 등록
	void childRegist(ChildRegisterRequest childInfo);
	
	// 아동 목록 조회
	List<ChildResponse> getChildList(String parentId);
	
	// 아동 아이디 찾기
	String getChildId(String parentId, String childName);
	
	// 아동 이름 반환
	String getChildName(String childId);

	//아동 나이, 성별 반환 
	ChildData getChildData(String child_id);

	//아동 문진표 등록 flag update
	int surveyFlag(String child_id);
	
	// 상담사 -> 예약한 아동 정보 조회
	ChildReservResponse getChildInfo(String childId);

}

