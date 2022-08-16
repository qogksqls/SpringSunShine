package com.ssafy.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.BExpertiseTherapist;
import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.repository.BExpertiseTherapistRepository;
import com.ssafy.web.db.repository.ExpertiseRepository;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistInfoResopnse;
import com.ssafy.web.model.response.TherapistResponse;
import com.ssafy.web.request.FindPwRequest;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyTotalRequest;
import com.ssafy.web.request.TheraRegisterInfo;
import com.ssafy.web.request.TheraRegisterRequest;

import lombok.RequiredArgsConstructor;
import reactor.util.IoUtils;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
	private final WebClient webClient;
	private final ExpertiseRepository expertiseRep;
	private final BExpertiseTherapistRepository bexpertiseRep;

	/* 부모 정보 조회 */
	@Override
	public Map<String, Object> parentInfo(String header, String parent_id) {
		ParentResponse parentInfo = webClient.get().uri("/authentication/parentinfo/" + parent_id).header("Authorization", header).retrieve()
				.bodyToMono(ParentResponse.class).block();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("parentInfo", parentInfo);
		return data;
	}

	/* 치료사 정보 조회 */
	@Override
	public Map<String, Object> theraInfo(String header, String thera_id) {
		TherapistResponse theraInfo = webClient.get().uri("/authentication/therainfo/" + thera_id).header("Authorization", header).retrieve()
				.bodyToMono(TherapistResponse.class).block();
		List<Expertise> list = expertiseRep.findByTheraIdjpql(thera_id);
		TherapistInfoResopnse therapistInfo = new TherapistInfoResopnse(theraInfo, list);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("theraInfo", therapistInfo);
		return data;
	}

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

	@Override
	public String findPass(String header, FindPwRequest findpw) {
		String res = webClient.post().uri("/authentication/findpw").bodyValue(findpw).header("Authorization", header).retrieve().bodyToMono(String.class).block();
		return res;
	}

	// 부모 정보 수정
	@Override
	public String parentModify(String header, String parent_id, ParentModifyRequest parentInfo) {
		String res = webClient.put().uri("/authentication/parent/" + parent_id).bodyValue(parentInfo).header("Authorization", header).retrieve()
				.bodyToMono(String.class).block();

		return res;
	}

	// 치료사 정보 수정 
	@Override
	public void theraModify(String header, String thera_id, TheraModifyTotalRequest tmtr) {
		webClient.put().uri("/authentication/therapist/" + thera_id).bodyValue(tmtr.makeTMR()).header("Authorization", header).retrieve()
				.bodyToMono(String.class).block();
		
		bexpertiseRep.deleteByTheraId(thera_id);
		
		List<BExpertiseTherapist> bextList = new ArrayList<BExpertiseTherapist>();
		for (int i = 0; i < tmtr.getExpertise().length; i++) {
			BExpertiseTherapist bext = new BExpertiseTherapist(thera_id, tmtr.getExpertise()[i]);
			bextList.add(bext);
		}
		bexpertiseRep.saveAll(bextList);

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
