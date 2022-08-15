package com.ssafy.web.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyTotalRequest;
import com.ssafy.web.request.TheraRegisterInfo;

public interface UserInfoService {
	Map<String, Object> parentInfo(String header, String parent_id);
	Map<String,Object> theraInfo(String header, String thera_id);
	void theraJoin(TheraRegisterInfo theraInfo);
	void parentJoin(ParentRegisterRequest parentInfo);
	String checkId(String id);
	ResponseEntity<?> findPass(String header, FindPwRequest findpw);
	ResponseEntity<?> parentModify(String header, String parent_id, ParentModifyRequest parentInfo);
	ResponseEntity<?> theraModify(String header, String thera_id, TheraModifyTotalRequest tmtr);
}
