package com.ssafy.web.service.child;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.web.dto.ChildData;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.child.ChildRegisterRequest;

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
}

