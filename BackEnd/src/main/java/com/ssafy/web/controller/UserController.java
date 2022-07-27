package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ssafy.web.request.ParentRegisterRequest;
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
		System.out.println(parentInfo.toString());
		
		Parent parent = userService.parentRegist(parentInfo);
		
		if(parent != null ) {
			//System.out.println("success");
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
		}else {
			//System.out.println("success");
			return ResponseEntity.status(200).body(BaseResponseBody.of(404, "fail"));}
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
	@PutMapping("/{user_id}")
	@ApiOperation(value="회원정보수정", notes="회원정보를 수정한다")
	@ApiResponses({
		@ApiResponse(code=200, message="성공"),
		@ApiResponse(code=401, message="실패"),
		@ApiResponse(code=500, message="서버오류")
	})
	public ResponseEntity<?>  userModify(@PathVariable(name="user_id") @ApiParam(value="사용자아이디" , required=true) String user_id){
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
