package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;

import reactor.core.publisher.Mono;

public interface ChildManagementService {
	
	// 아동 목록 조회
	List<ChildResponse> getChildList(String parentId);
	
	// 아동 등록
	void childRegist(ChildRegisterRequest childInfo);
}
