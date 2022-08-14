package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraRegisterInfo;
import com.ssafy.web.request.TheraRegisterRequest;
import com.ssafy.web.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
	private final UserInfoService userInfoService;
	
	
	@GetMapping("/{user_id}")
	public Map<String,Object> userInfo(@PathVariable("user_id") String user_id) {
		Map<String,Object> data = new HashMap<String, Object>();
		
		/*부모 회원정보 조회*/
		if(user_id.charAt(0)=='p'){
			data = userInfoService.parentInfo(user_id);
			return data;
		}
		
		/*치료사 회원정보 조회*/
		else if(user_id.charAt(0)=='t'){
			data = userInfoService.theraInfo(user_id);
			return data;
		}
		
		data.put("message","fail");
		return null;
	}
	
	@PostMapping("/therapist")
	public ResponseEntity<?>  theraRegist(@RequestBody TheraRegisterInfo theraInfo){
		userInfoService.theraJoin(theraInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	
	/*부모 회원가입*/
	@PostMapping("/parent")
	public ResponseEntity<?>  parentRegist(@RequestBody ParentRegisterRequest parentInfo){
		userInfoService.parentJoin(parentInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));	
	}
	
}
