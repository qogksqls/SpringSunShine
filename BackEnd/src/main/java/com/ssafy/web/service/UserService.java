package com.ssafy.web.service;

import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistResponse;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraRegisterRequest;

public interface UserService {
	// 치료사 회원가입 
	void theraRegist(TheraRegisterRequest theraInfo);
	// 부모회원가입 
	void parentRegist(ParentRegisterRequest parentInfo);
	
	//아이디 중복검사
	int checkId(String id);
	
	//이메일 중복검사
	int checkEmail(String myemail);
	
	//부모 회원정보 조회
	ParentResponse getParentInfo(String user_id);
	
	//치료사 회원정보 조회 
	TherapistResponse getTheraInfo(String user_id);
	
	//아이디가 user_id인 사람의 회원 정보를 parentInfo 로 교체 
	void parentModify(String user_id, ParentRegisterRequest parentInfo);

}
