package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

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
			data = userInfoService.parentInfo( header, user_id);
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
	@Caching(evict= {
			@CacheEvict(value="consultTherapist", allEntries=true),
			@CacheEvict(value="consultTherapistAndChildId", allEntries=true),
			@CacheEvict(value="consultParentAndChildId", allEntries=true),
			@CacheEvict(value="consultParent", allEntries=true)
	})
	@PutMapping("/parent/{parent_id}")
	public ResponseEntity<?> parentModify(HttpServletRequest request, @PathVariable String parent_id , @RequestBody ParentModifyRequest parentInfo){
		System.out.println(request.getHeader("Authorization"));
		String header= request.getHeader("Authorization");
		return userInfoService.parentModify(header, parent_id, parentInfo);
		
	}
	
	/**치료사 회원정보 수정 */
	@Caching(evict= {
			@CacheEvict(value="recommendThera", allEntries=true),
			@CacheEvict(value="recommendTheraAll", allEntries=true),
			@CacheEvict(value="recommendTheraByChildId", allEntries=true),
			@CacheEvict(value="consultTherapist", allEntries=true),
			@CacheEvict(value="consultTherapistAndChildId", allEntries=true),
			@CacheEvict(value="consultParentAndChildId", allEntries=true),
			@CacheEvict(value="consultParent", allEntries=true)
	})
	@PutMapping("/therapist/{thera_id}")
	public ResponseEntity<?> theraModify(HttpServletRequest request, @PathVariable String thera_id, 
			@RequestBody TheraModifyTotalRequest tmtr) {
		System.out.println(request.getHeader("Authorization"));
		String header= request.getHeader("Authorization");
		return userInfoService.theraModify(header, thera_id, tmtr);
		
	}
	
	/*상담사 회원가입*/
	@Caching(evict= {
			@CacheEvict(value="recommendThera", allEntries=true),
			@CacheEvict(value="recommendTheraAll", allEntries=true),
			@CacheEvict(value="recommendTheraByChildId", allEntries=true)
	})
	@PostMapping("/therapist")
	public String  theraRegist(@RequestPart MultipartFile profile ,@RequestPart(value = "theraInfo") TheraRegisterInfo theraInfo){
		// 유효성 검사 
		String id = theraInfo.getId();
		String pw = theraInfo.getPassword();
		String name = theraInfo.getName();
		String email = theraInfo.getEmail();
		String phone = theraInfo.getPhone();
		String address =theraInfo.getAddress();
		if(id.length()==0 || pw.length()==0 || name.length()==0 || email.length()==0 
				|| phone.length()==0 || address.length()==0 ) {
			return "fail";
		}
		else if(userInfoService.checkId(id).equals("fail")) {
			return "fail";
		}else if(userInfoService.checkEmail(email).equals("fail")) {
			return "fail";
			}
		userInfoService.theraJoin(profile, theraInfo);
		return "success";
	}
	
	/*부모 회원가입*/
	@PostMapping("/parent")
	public ResponseEntity<?>  parentRegist(@RequestBody ParentRegisterRequest parentInfo){
		// 유효성 검사 
		String id = parentInfo.getId();
		String pw = parentInfo.getPassword();
		String name = parentInfo.getName();
		String email = parentInfo.getEmail();
		String phone = parentInfo.getPhone();
		String address =parentInfo.getAddress();
		if(id.length()==0 || pw.length()==0 || name.length()==0 || email.length()==0 
				|| phone.length()==0 || address.length()==0 ) {
			return new ResponseEntity<String>("모든값 입력필요", HttpStatus.BAD_REQUEST);
		}
		else if(userInfoService.checkId(id).equals("fail")) {
			return new ResponseEntity<String>("아이디 중복", HttpStatus.BAD_REQUEST);
		}else if(userInfoService.checkEmail(email).equals("fail")) {
			return new ResponseEntity<String>("이메일 중복", HttpStatus.BAD_REQUEST);
		}
		
		userInfoService.parentJoin(parentInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));	
	}
	
	/**비밀번호 찾기*/
	@PostMapping("/findpw")
	public ResponseEntity<?> findPass(HttpServletRequest request, @RequestBody FindPwRequest findpw){
		System.out.println(request.getHeader("Authorization"));
		String header= request.getHeader("Authorization");
		return userInfoService.findPass(header,findpw);
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
