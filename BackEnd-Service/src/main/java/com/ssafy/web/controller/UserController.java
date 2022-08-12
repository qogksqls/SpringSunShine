package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyRequest;
import com.ssafy.web.request.TheraModifyTotalRequest;
import com.ssafy.web.request.TheraRegisterInfo;
import com.ssafy.web.request.TheraRegisterRequest;
import com.ssafy.web.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
/**SERVICE SERVER*/
public class UserController {
	private final UserInfoService userInfoService;
	
	
	@GetMapping("/{user_id}")
	public Map<String,Object> userInfo(HttpServletRequest request, @PathVariable("user_id") String user_id) {
		System.out.println(request.getHeader("Authorization"));
		String header= request.getHeader("Authorization");
		Map<String,Object> data = new HashMap<String, Object>();
		
		/*부모 회원정보 조회*/
		if(user_id.charAt(0)=='p'){
			data = userInfoService.parentInfo(header, user_id);
			return data;
		}
		
		/*치료사 회원정보 조회*/
		else if(user_id.charAt(0)=='t'){
			data = userInfoService.theraInfo(header, user_id);
			return data;
		}
		
		data.put("message","fail");
		return null;
	}
	
	/**부모 회원정보 수정 */
	@PutMapping("/parent/{parent_id}")
	public ResponseEntity<?> parentModify(HttpServletRequest request, @PathVariable String parent_id , @RequestBody ParentModifyRequest parentInfo){
		System.out.println(request.getHeader("Authorization"));
		String header= request.getHeader("Authorization");
		String res= userInfoService.parentModify(header, parent_id, parentInfo);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@PutMapping("/therapist/{thera_id}")
	public String theraModify(HttpServletRequest request, @PathVariable String thera_id, 
			@RequestBody TheraModifyTotalRequest tmtr) {
		System.out.println(request.getHeader("Authorization"));
		String header= request.getHeader("Authorization");
		userInfoService.theraModify(header, thera_id, tmtr);
		return "success";
	}
	
	
	/*상담사 회원가입*/
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
	
	/**비밀번호 찾기*/
	@PostMapping("/findpw")
	public ResponseEntity<?> findPass(HttpServletRequest request, @RequestBody FindPwRequest findpw){
		System.out.println(request.getHeader("Authorization"));
		String header= request.getHeader("Authorization");
		String res = userInfoService.findPass(header,findpw);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	/**아이디 중복검사 */
	@GetMapping("/checkid/{id}")
	public ResponseEntity<String> checkid(@PathVariable String id) {
		if(id == null) {
			return new ResponseEntity<String>("null" , HttpStatus.NO_CONTENT);
		}
		String res = userInfoService.checkId(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);
		
	}
	
	
	
}
