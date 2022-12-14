package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistResponse;
import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyRequest;
import com.ssafy.web.request.TheraRegisterRequest;
import com.ssafy.web.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(value="회원관리 API", tags={"User"})
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserController {
	

	
	@Autowired
	UserService userService;

	/*상담사 회원가입*/
	@PostMapping("/therapist")
	@ApiOperation(value="상담사 회원가입", notes="<strong>상담사</strong> 로 회원가입한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public String theraRegist(
			@RequestPart(required = false) MultipartFile profile,
			@RequestPart @ApiParam(value="상담사 회원가입 요청 정보", required=true) TheraRegisterRequest theraInfo){
		log.debug("user controller-상담사회원가입");
		log.debug(profile.getOriginalFilename());
		log.debug(theraInfo.toString());
		
		String userId =userService.theraRegist(profile,theraInfo);
		return userId;
	}
	
	/*부모 회원가입*/
	@PostMapping("/parent")
	@ApiOperation(value="부모 회원가입", notes="<strong>사용자</strong> 로 회원가입한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public void parentRegist(@RequestBody @ApiParam(value="부모 회원가입 요청 정보", required=true) ParentRegisterRequest parentInfo){
		userService.parentRegist(parentInfo);
	}
	
	/*아이디 중복검사*/
	@GetMapping("/checkid/{id}")
	@ApiOperation(value="아이디 중복검사", notes="<strong>아이디 중복검사</strong> 를 한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public String checkId(@PathVariable String id) {
		System.out.println(id);
		int res = userService.checkId(id);
		
		if(res == 1 ) {
			return "success";
			
		}
		else return "fail";
	}
	
	/*이메일 중복검사*/
	@GetMapping("/checkEmail/{email}")
	public String checkEmail(@PathVariable String email) {
		int res= userService.checkEmail(email);
		if(res == 1) return "success";
		else return "fail";
	}
	
//	/*치료사 회원정보 조회*/
//	@GetMapping("/therainfo/{user_id}")
//	public TherapistResponse theraInfo(@PathVariable String user_id){
//		TherapistResponse tresult= userService.getTheraInfo(user_id);
//		return tresult;
//	}
//	
//	/*부모 회원정보 조회*/
//	@GetMapping("/parentinfo/{user_id}")
//	public ParentResponse parentInfo(@PathVariable String user_id){
//		
//		ParentResponse presult = userService.getParentInfo(user_id);	
//		return presult;
//	}
//	
//	/*부모 회원정보 수정*/
//	@PutMapping("/parent/{parent_id}")
//	public String parentModify(@PathVariable String parent_id, @RequestBody ParentModifyRequest parentInfo){
//		//parentInfo : 수정할 부모 정보가 담겨진 객체 
//		//부모 아이디가 아닐때 
//		if(parent_id.charAt(0) != 'p') 
//			return "fail";
//		int res = userService.parentModify(parent_id, parentInfo); 
//		if(res==0) {
//			return "fail";
//		}
//		return "success";
//	}
//	
//	/*치료사 회원정보 수정*/
//	@PutMapping("/therapist/{user_id}")
//	public ResponseEntity<?> theraModify(@PathVariable String user_id, @RequestBody TheraModifyRequest theraInfo){
//		//theraInfo : 수정할 치료사 정보 
//		if(user_id.charAt(0) != 't') 
//			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "유효하지 않은 사용자"));
//		int res = userService.theraModify(user_id, theraInfo);
//		if(res==0) {
//			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "유효하지 않은 사용자"));
//		}
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
//	}
//	
//	/**회원탈퇴*/
//	@DeleteMapping("/{id}")
//	@ApiOperation(value="회원탈퇴",  notes="<strong>user_id</strong> 로 회원정보를 탈퇴한다")
//	@ApiResponses({
//		@ApiResponse(code=200, message="성공"),
//		@ApiResponse(code=401, message="실패"),
//		@ApiResponse(code=500, message="서버오류")
//	})
//	public ResponseEntity<?>  userDelete(){
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
//	}
//	
//	/**비밀번호 찾기*/
//	@PostMapping("/findpw")
//	public String findPass(@RequestBody FindPwRequest findpw){
//		String id = findpw.getId(); //13자리 난수 아님 
//		String email = findpw.getEmail();
//		try {
//			int res = userService.findPass(id, email);
//			if(res==0) {
//				return "fail";
//			}
//			return "success";
//			//프론트와 상의 후 어떻게 할건지 결정 
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "fail";
//		} 
//	}
	
}
