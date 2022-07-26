package com.ssafy.web.service;

import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraRegisterRequest;

public interface UserService {
	// 치료사 회원가입 
	void theraRegist(TheraRegisterRequest theraInfo);
	// 부모회원가입 
	void parentRegist(ParentRegisterRequest parentInfo);

	

}
