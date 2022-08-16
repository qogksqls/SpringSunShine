package com.ssafy.web.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyTotalRequest;
import com.ssafy.web.request.TheraRegisterInfo;

public interface UserInfoService {
	Map<String, Object> parentInfo(String header, String parent_id);
	Map<String,Object> theraInfo(String header, String thera_id);
	void theraJoin(MultipartFile profile, TheraRegisterInfo theraInfo);
	void parentJoin(ParentRegisterRequest parentInfo);
	String checkId(String id);
	String findPass(String header, FindPwRequest findpw);
	String parentModify(String header, String parent_id, ParentModifyRequest parentInfo);
	void theraModify(String header, String thera_id, TheraModifyTotalRequest tmtr);
}
