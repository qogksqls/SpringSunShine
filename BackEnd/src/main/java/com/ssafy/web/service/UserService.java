package com.ssafy.web.service;

import com.ssafy.web.db.entity.User;
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

}
