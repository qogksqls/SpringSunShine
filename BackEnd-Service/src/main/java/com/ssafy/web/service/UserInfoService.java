package com.ssafy.web.service;

import java.util.Map;

public interface UserInfoService {
	Map<String, Object> parentInfo(String parent_id);
	Map<String,Object> theraInfo(String thera_id);
}
