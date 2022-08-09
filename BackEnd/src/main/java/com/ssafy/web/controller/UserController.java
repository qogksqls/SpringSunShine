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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistResponse;
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

@RestController
@Api(value="회원관리 API", tags={"User"})
@RequestMapping("/user")
@CrossOrigin("*")
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
	public ResponseEntity<?>  theraRegist(@RequestBody @ApiParam(value="상담사 회원가입 요청 정보", required=true) TheraRegisterRequest theraInfo){
		System.out.println(theraInfo.getAcademicCareers());
		userService.theraRegist(theraInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	
	/*부모 회원가입*/
	@PostMapping("/parent")
	@ApiOperation(value="부모 회원가입", notes="<strong>사용자</strong> 로 회원가입한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?>  parentRegist(@RequestBody @ApiParam(value="부모 회원가입 요청 정보", required=true) ParentRegisterRequest parentInfo){
		
		userService.parentRegist(parentInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
		
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
	
	/*회원정보 조회*/
	@GetMapping("/{user_id}")
	public ResponseEntity<?> userInfo(@PathVariable String user_id){
		/*부모 회원정보 조회*/
		if(user_id.charAt(0)=='p'){
			ParentResponse presult = userService.getParentInfo(user_id);	
			return new ResponseEntity<ParentResponse>(presult, HttpStatus.OK);		
			
			
		}
		/*치료사 회원정보 조회*/
		else if(user_id.charAt(0)=='t'){
			TherapistResponse tresult= userService.getTheraInfo(user_id);
			return new ResponseEntity<TherapistResponse>(tresult, HttpStatus.OK);
		}
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	/*부모 회원정보 수정*/
	@PutMapping("/parent/{user_id}")
	public ResponseEntity<?>  parentModify(@PathVariable String user_id, @RequestBody ParentModifyRequest parentInfo){
		//parentInfo : 수정할 부모 정보가 담겨진 객체 
		//부모 아이디가 아닐때 
		if(user_id.charAt(0) != 'p') 
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "유효하지 않은 사용자"));
		int res = userService.parentModify(user_id, parentInfo); 
		if(res==0) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "유효하지 않은 사용자"));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
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
	
}
