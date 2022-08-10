package com.ssafy.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.repository.ExpertiseRepository;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{
	private final WebClient webClient;
	private final ExpertiseRepository expertiseRep;
	
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
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("expertises", list);
		data.put("theraInfo", theraInfo);
		return data;
	}

}
