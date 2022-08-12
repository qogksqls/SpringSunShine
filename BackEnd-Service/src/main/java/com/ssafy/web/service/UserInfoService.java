package com.ssafy.web.service;

import java.util.Map;

import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraRegisterInfo;

public interface UserInfoService {
	Map<String, Object> parentInfo(String parent_id);
	Map<String,Object> theraInfo(String thera_id);
	void theraJoin(TheraRegisterInfo theraInfo);
	void parentJoin(String header, ParentRegisterRequest parentInfo);
	String checkId(String id);
	String findPass(FindPwRequest findpw);
}
