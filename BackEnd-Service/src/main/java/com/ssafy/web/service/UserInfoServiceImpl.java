package com.ssafy.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.UnknownHttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.ssafy.web.db.entity.BExpertiseTherapist;
import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.repository.BExpertiseTherapistRepository;
import com.ssafy.web.db.repository.ExpertiseRepository;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistInfoResopnse;
import com.ssafy.web.model.response.TherapistResponse;
import com.ssafy.web.model.response.TokenErrorResponse;
import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyTotalRequest;
import com.ssafy.web.request.TheraRegisterInfo;
import com.ssafy.web.request.TheraRegisterRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.util.IoUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
	private final WebClient webClient;
	private final ExpertiseRepository expertiseRep;
	private final BExpertiseTherapistRepository bexpertiseRep;

	
	/** 치료사 회원가입 */
	@Override
	public void theraJoin(MultipartFile profile, TheraRegisterInfo info) {
		TheraRegisterRequest theraInfo = info.makeTheraRegisterRequest();
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		multiValueMap.add("theraInfo", theraInfo);
		try {
			Resource r = new FileSystemResource(profile.getBytes(), profile.getOriginalFilename());
			multiValueMap.add("profile", r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
		String userId = webClient.post().uri("/user/therapist")
				.contentType(MediaType.MULTIPART_FORM_DATA)
		        .accept(MediaType.APPLICATION_JSON)
				.bodyValue(multiValueMap).retrieve().bodyToMono(String.class)
				.block();
		List<BExpertiseTherapist> bextList = new ArrayList<BExpertiseTherapist>();

		for (int i = 0; i < info.getExpertise().size(); i++) {
			BExpertiseTherapist bext = new BExpertiseTherapist(userId, info.getExpertise().get(i));
			bextList.add(bext);
		}
		bexpertiseRep.saveAll(bextList);
	}

	/** 부모 회원가입 */
	@Override
	public void parentJoin(ParentRegisterRequest parentInfo) {
		webClient.post().uri("/user/parent").bodyValue(parentInfo).retrieve()
				.bodyToMono(String.class).block();
	}

	// 아이디 중복검사
	@Override
	public String checkId(String id) {
		String res = webClient.get().uri("/user/checkid/" + id).retrieve().bodyToMono(String.class).block();

		return res;
	}
	
	// 이메일 중복검사
	@Override
	public String checkEmail(String email) {
		String res = webClient.get().uri("/user/checkEmail/" + email).retrieve().bodyToMono(String.class).block();

		return res;
	}

	/*======================= 토큰 검사 필요한 api ==========================*/
	/* 부모 정보 조회 */
	@Override
	public Map<String, Object> parentInfo(String header, String parent_id) {
		ParentResponse parentInfo = new ParentResponse(); 
		TokenErrorResponse er = new TokenErrorResponse();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			parentInfo= webClient.get().uri("/authentication/parentinfo/"+parent_id)
			.header("Authorization", header)
			.retrieve().bodyToMono(ParentResponse.class).block();
			//토큰사용자가 틀림 
			if(parentInfo.getId().equals("wrong-token")) {
				er.setStatusCode(1000);
				er.setMessage("토큰 사용자 오류");
				data.put("errorInfo", er);
				return data;
			}
			data.put("parentInfo", parentInfo);
		}catch(UnknownHttpStatusCodeException e) {
			int status = e.getRawStatusCode();
			//유효기간 만료 된 토큰 
			if(status == 1001) {
				er.setStatusCode(1001);
				er.setMessage("토큰기한이 만료, 재발급 요청 ");
			}
			//옳지 않은 토큰 
			if(status == 1002) {
				er.setStatusCode(1002);
				er.setMessage("틀린 토큰 or 올바르지 않은 형식");
			}
			data.put("errorInfo", er);
		}catch(WebClientResponseException e) {
			int status = e.getRawStatusCode();
			er.setStatusCode(status);
			er.setMessage("토큰 값 없음 or 서버 오류");
			data.put("errorInfo", er);
		}
		return data;
	}

	/* 치료사 정보 조회 */
	@Override
	public Map<String, Object> theraInfo(String header, String thera_id) {
		TherapistResponse theraInfo = new TherapistResponse();
		TokenErrorResponse er = new TokenErrorResponse();
		Map<String, Object> data = new HashMap<String, Object>();
		try{
			theraInfo = webClient.get().uri("/authentication/therainfo/" + thera_id)
				.header("Authorization", header).retrieve()
				.bodyToMono(TherapistResponse.class).block();
			//토큰사용자가 틀림 
			if(theraInfo.getId().equals("wrong-token")) {
				er.setStatusCode(1000);
				er.setMessage("토큰 사용자 오류");
				data.put("errorInfo", er);
				return data;
			}
			List<Expertise> list = expertiseRep.findByTheraIdjpql(thera_id);
			TherapistInfoResopnse therapistInfo = new TherapistInfoResopnse(theraInfo, list);
			data.put("theraInfo", therapistInfo);
		}catch(UnknownHttpStatusCodeException e) {
			
			int status = e.getRawStatusCode();
			//유효기간 만료 된 토큰 
			if(status == 1001) {
				er.setStatusCode(1001);
				er.setMessage("토큰기한이 만료, 재발급 요청 ");
			}
			//옳지 않은 토큰 
			if(status == 1002) {
				er.setStatusCode(1002);
				er.setMessage("틀린 토큰 or 올바르지 않은 형식");
			}
			data.put("errorInfo", er);
		}catch(WebClientResponseException e) {
			int status = e.getRawStatusCode();
			er.setStatusCode(status);
			er.setMessage("토큰 값 없음 or 서버 오류");
			data.put("errorInfo", er);
		}
		return data;
	}
	
	@Override
	public ResponseEntity<?> findPass(String header, FindPwRequest findpw) {
		TokenErrorResponse er = new TokenErrorResponse();
		try{
			String res = webClient.post().uri("/authentication/findpw").bodyValue(findpw).header("Authorization", header)
					.retrieve().bodyToMono(String.class).block();
			//토큰사용자가 틀림 
			if(res.equals("wrong-token")) {
				er.setStatusCode(1000);
				er.setMessage("토큰 사용자 오류");
				return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
		}catch(UnknownHttpStatusCodeException e) {
			int status = e.getRawStatusCode();
			//유효기간 만료 된 토큰 
			if(status == 1001) {
				er.setStatusCode(1001);
				er.setMessage("토큰기한이 만료, 재발급 요청 ");
			}
			//옳지 않은 토큰 
			if(status == 1002) {
				er.setStatusCode(1002);
				er.setMessage("틀린 토큰 or 올바르지 않은 형식");
			}
			
			return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}catch(WebClientResponseException e) {
			int status = e.getRawStatusCode();
			er.setStatusCode(status);
			er.setMessage("토큰 값 없음 or 서버 오류");
			return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}
	}

	// 부모 정보 수정
	@Override
	public ResponseEntity<?> parentModify(String header, String parent_id, ParentModifyRequest parentInfo) {
		TokenErrorResponse er = new TokenErrorResponse();
		try {
		String res= webClient.put().uri("/authentication/parent/" + parent_id).bodyValue(parentInfo).header("Authorization", header).retrieve()
				.bodyToMono(String.class).block();
		//토큰사용자가 틀림 
		if(res.equals("wrong-token")) {
			er.setStatusCode(1000);
			er.setMessage("토큰 사용자 오류");
			return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
		}catch(UnknownHttpStatusCodeException e) {
			int status = e.getRawStatusCode();
			//유효기간 만료 된 토큰 
			if(status == 1001) {
				er.setStatusCode(1001);
				er.setMessage("토큰기한이 만료, 재발급 요청 ");
			}
			//옳지 않은 토큰 
			if(status == 1002) {
				er.setStatusCode(1002);
				er.setMessage("틀린 토큰 or 올바르지 않은 형식");
			}
			
			return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}catch(WebClientResponseException e) {
			int status = e.getRawStatusCode();
			er.setStatusCode(status);
			er.setMessage("토큰 값 없음 or 서버 오류");
			return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}
	}

	// 치료사 정보 수정 
	@Override
	public ResponseEntity<?> theraModify(String header, String thera_id, TheraModifyTotalRequest tmtr) {
		TokenErrorResponse er = new TokenErrorResponse();
		try{
			String res =webClient.put().uri("/authentication/therapist/" + thera_id).bodyValue(tmtr.makeTMR()).header("Authorization", header).retrieve()
				.bodyToMono(String.class).block();
			if(res.equals("fail")) {
				return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
			}
			//토큰사용자가 틀림 
			if(res.equals("wrong-token")) {
				er.setStatusCode(1000);
				er.setMessage("토큰 사용자 오류");
				return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
			}
			
			bexpertiseRep.deleteByTheraId(thera_id);
			List<BExpertiseTherapist> bextList = new ArrayList<BExpertiseTherapist>();
			for (int i = 0; i < tmtr.getExpertise().length; i++) {
			BExpertiseTherapist bext = new BExpertiseTherapist(thera_id, tmtr.getExpertise()[i]);
			bextList.add(bext);
			}
			bexpertiseRep.saveAll(bextList);
			return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
		}catch(UnknownHttpStatusCodeException e) {
			int status = e.getRawStatusCode();
			//유효기간 만료 된 토큰 
			if(status == 1001) {
				er.setStatusCode(1001);
				er.setMessage("토큰기한이 만료, 재발급 요청 ");
			}
			//옳지 않은 토큰 
			if(status == 1002) {
				er.setStatusCode(1002);
				er.setMessage("틀린 토큰 or 올바르지 않은 형식");
			}
			
			return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}catch(WebClientResponseException e) {
			int status = e.getRawStatusCode();
			er.setStatusCode(status);
			er.setMessage("토큰 값 없음 or 서버 오류");
			return new ResponseEntity<TokenErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}
	}

	
	public static class FileSystemResource extends ByteArrayResource {

	    private String fileName;

	    public FileSystemResource(byte[] byteArray, String filename) {
	        super(byteArray);
	        this.fileName = filename;
	    }

	    public String getFilename() {
	        return fileName;
	    }

	    public void setFilename(String fileName) {
	        this.fileName = fileName;
	    }
	}
}
