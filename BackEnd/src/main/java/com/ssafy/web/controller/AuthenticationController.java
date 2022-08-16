package com.ssafy.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistResponse;
import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.TheraModifyRequest;
import com.ssafy.web.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/authentication") //jwt 필터를 거쳐서 인증된 사용자만 거칠 수 있도록 
public class AuthenticationController {
 
	@Autowired
	UserService userService;
	

	/*치료사 회원정보 조회*/
	@GetMapping("/therainfo/{user_id}")
	public TherapistResponse theraInfo(@PathVariable String user_id) throws IOException{
		TherapistResponse tresult= userService.getTheraInfo(user_id);
		return tresult;
	}
	
	/*부모 회원정보 조회*/
	@GetMapping("/parentinfo/{user_id}")
	public ParentResponse parentInfo(@PathVariable String user_id){
		
		ParentResponse presult = userService.getParentInfo(user_id);	
		return presult;
	}
	

	/*부모 회원정보 수정*/
	@PutMapping("/parent/{parent_id}")
	public String parentModify(@PathVariable String parent_id, @RequestBody ParentModifyRequest parentInfo){
		//parentInfo : 수정할 부모 정보가 담겨진 객체 
		//부모 아이디가 아닐때 
		if(parent_id.charAt(0) != 'p') 
			return "fail";
		int res = userService.parentModify(parent_id, parentInfo); 
		if(res==0) {
			return "fail";
		}
		return "success";
	}
	
	/*치료사 회원정보 수정*/
	@PutMapping("/therapist/{user_id}")
	public ResponseEntity<?> theraModify(@PathVariable String user_id, @RequestBody TheraModifyRequest theraInfo){
		//theraInfo : 수정할 치료사 정보 
		if(user_id.charAt(0) != 't') 
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "유효하지 않은 사용자"));
		int res = userService.theraModify(user_id, theraInfo);
		if(res==0) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "유효하지 않은 사용자"));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	
	/**회원탈퇴*/
	@DeleteMapping("/{id}")
	@ApiOperation(value="회원탈퇴",  notes="<strong>user_id</strong> 로 회원정보를 탈퇴한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?>  userDelete(){
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	
	/**비밀번호 찾기*/
	@PostMapping("/findpw")
	public String findPass(@RequestBody FindPwRequest findpw){
		String id = findpw.getId(); //13자리 난수 아님 
		String email = findpw.getEmail();
		try {
			int res = userService.findPass(id, email);
			if(res==0) {
				return "fail";
			}
			return "success";
			//프론트와 상의 후 어떻게 할건지 결정 
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		} 
	}
}
