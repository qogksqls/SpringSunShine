package com.ssafy.web.service;

import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistCareer;
import com.ssafy.web.model.response.TherapistResponse;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyRequest;
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
	
	//부모 회원정보 수정 
	int parentModify(String user_id, ParentModifyRequest parentInfo);
	
	//치료사 회원정보 수정 
	int theraModify(String user_id, TheraModifyRequest theraInfo);
	
	//비밀번호 찾기 (아이디, 이메일)
	int findPass(String id, String email) throws Exception;

	// 보호자 이름 반환
	String getParentName(String parentId);
	
	// 상담사 이름 반환
	String getTheraName(String theraId);

}
