package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.model.response.ChildResponse;

public interface ChildManagementService {
	
	// 아동 목록 조회
	List<ChildResponse> getChildList(String parentId);
}
