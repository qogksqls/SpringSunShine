package com.ssafy.web.service.child;

import java.util.List;

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
}

