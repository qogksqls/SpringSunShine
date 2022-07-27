package com.ssafy.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.UserLoginRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/*
 * *인증 ( 로그인 )*/

@Api(value="인증 API", tags= {"Auth"})
@RestController
@RequestMapping("/auth")
public class AuthController {

	/* 일반로그인 */
	@PostMapping("/login")
	@ApiOperation(value="일반 로그인", notes="<strong>id와 password</strong>로 로그인한다.")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?>  userLogin(@RequestBody @ApiParam(value="로그인 요청 정보", required=true) UserLoginRequest loginInfo){
		/**추후에 토큰 발급해서 토믄도 같이 보내야 함 !! */
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
	

	/* 로그아웃 */
	@GetMapping("/logout")
	@ApiOperation(value="일반 로그아웃", notes="로그아웃한다.")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?>  userLogout(){
		/**추후에 토큰 발급해서 토믄도 같이 보내야 함 !! */
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}
}
