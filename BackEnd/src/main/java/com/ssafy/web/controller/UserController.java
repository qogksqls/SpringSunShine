package com.ssafy.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="회원관리 API", tags={"User"})
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	/*회원가입*/
	@PostMapping
	@ApiOperation(value="회원가입", notes="<strong>상담사 또는 사용자</strong> 로 회원가입한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?>  userRegister(){
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	
	/*회원정보 조회*/
	@GetMapping("/{user_id}")
	@ApiOperation(value="회원정보조회", notes="<strong>user_id</strong> 로 회원정보를 조회한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?> userInfo(){
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	
	/*회원정보 수정*/
	@PutMapping
	@ApiOperation(value="회원정보수정", notes="회원정보를 수정한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?>  userModify(){
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	
	/**회원탈퇴*/
	@DeleteMapping("/{user_id}")
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
