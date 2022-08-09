package com.ssafy.web.service.child;

import com.ssafy.web.request.child.ChildRegisterRequest;

public interface ChildService {

	// 아동 등록
	void childRegist(ChildRegisterRequest childInfo);
	
	// 아동 삭제
	void childDelete(String childId);
	
	// 아동 아이디 찾기
	String getChildId(String childName, String parentId);
}
