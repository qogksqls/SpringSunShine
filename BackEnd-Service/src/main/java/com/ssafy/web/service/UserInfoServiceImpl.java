package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
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
import com.ssafy.web.request.TheraRegisterInfo;
import com.ssafy.web.request.TheraRegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{
	private final WebClient webClient;
	private final ExpertiseRepository expertiseRep;
	private final BExpertiseTherapistRepository bexpertiseRep;
	
	/* 부모 정보 조회 */
	@Override
	public Map<String, Object> parentInfo(String parent_id) {
		ParentResponse parentInfo = 
				webClient.get()
				.uri("/user/parentinfo/"+parent_id)
				.retrieve()
				.bodyToMono(ParentResponse.class).block();
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("parentInfo", parentInfo);
		return data;
	}
	
	/* 치료사 정보 조회*/
	@Override
	public Map<String,Object> theraInfo(String thera_id) {
		
		List<Expertise> list = expertiseRep.findByTheraIdjpql(thera_id);
		TherapistResponse theraInfo = 
				webClient.get()
				.uri("/user/therainfo/"+thera_id)
				.retrieve()
				.bodyToMono(TherapistResponse.class).block();
		TherapistInfoResopnse therapistInfo = new TherapistInfoResopnse(theraInfo, list);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("theraInfo", therapistInfo);
		return data;
	}

	@Override
	public void theraJoin(TheraRegisterInfo info) {
		TheraRegisterRequest theraInfo =info.makeTheraRegisterRequest(); 
		String userId = 
				webClient.post()
				.uri("/user/therapist")
				.bodyValue(theraInfo)
				.retrieve()
				.bodyToMono(String.class).block();
		List<BExpertiseTherapist> bextList = new ArrayList<BExpertiseTherapist>();
		
		for(int i=0; i<info.getExpertise().size(); i++) {
			BExpertiseTherapist bext = new BExpertiseTherapist(userId, info.getExpertise().get(i));
			bextList.add(bext);
		}
		bexpertiseRep.saveAll(bextList);
	}

	@Override
	public void parentJoin(String header , ParentRegisterRequest parentInfo) {
		webClient.post()
		.uri("/user/parent")
		.bodyValue(parentInfo)
		.header("Authorization",header )
		.retrieve()
		.bodyToMono(String.class).block();
	}

	//아이디 중복검사
	@Override
	public String checkId(String id) {
		String res = webClient.get()
		.uri("/user/checkid/"+id)
		.retrieve()
		.bodyToMono(String.class).block();
		
		return res;
	}

	@Override
	public String findPass(FindPwRequest findpw) {
		String res = 
				webClient.post()
				.uri("/user/findpw")
				.bodyValue(findpw)
				.retrieve()
				.bodyToMono(String.class).block();
		return res;
	}

	//부모 정보 수정 
	@Override
	public String parentModify(String parent_id, ParentModifyRequest parentInfo) {
		String res= webClient.put()
				.uri("/user/parent/"+parent_id)
				.bodyValue(parentInfo)
				.retrieve()
				.bodyToMono(String.class).block();
		
		return res;
	}

}
