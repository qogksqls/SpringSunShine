package com.ssafy.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.BExpertiseChild;
import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.repository.BExpertiseChildRepository;
import com.ssafy.web.db.repository.BExpertiseTherapistRepository;
import com.ssafy.web.model.response.RecommendTherapistResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BExpertiseTherapistServiceImpl implements BExpertiseTherapistService {
	private final BExpertiseTherapistRepository BETRepo;
	private final BExpertiseChildRepository BECRepo;
	private final WebClient webClient;

//	@Override
//	public RecommendTherapistResponse[] recommendTherapistList(int expertise_no) {
//		List<String> betmList = BETRepo.findByExpertiseNo(expertise_no);
//		
//		RecommendTherapistResponse[] apiList = webClient.post().uri("/therapist/recommed")
//				.bodyValue(betmList).retrieve()
//				.bodyToMono(RecommendTherapistResponse[].class).block();
//		System.out.println(apiList);
//		return apiList;
//	}
	
	@Override
	public RecommendTherapistResponse[] recommendTherapistList(int expertise_no) {
		List<String> betmList = BETRepo.findByExpertiseNo(expertise_no);
		
		RecommendTherapistResponse[] apiList = 
				webClient.get()
				.uri(builder -> builder.path("/therapist/recommed/")
                        .queryParam("betmList",betmList)
                        .build())
				.retrieve()
				.bodyToMono(RecommendTherapistResponse[].class).block();
		return apiList;
	}

	@Override
	public RecommendTherapistResponse[] recommendTherapistAll() {
		RecommendTherapistResponse[] apiList = 
				webClient.get()
				.uri("/therapist/recommedall")
				.retrieve()
				.bodyToMono(RecommendTherapistResponse[].class).block();
		return apiList;
	}

	//아동의 증상번호 불러오기
	@Override
	public int getChildExp(String parent_id, String child_name) {
		String childId= webClient.get().uri("/child/" + parent_id + "/" + child_name).retrieve().
				bodyToMono(String.class).block();
		BExpertiseChild childEx = BECRepo.findByChildId(childId);
		int expno = childEx.getExpertise().getExpertiseNo();
		System.out.println(childId+"의 증상 : "+expno);
		return expno;
	}

}
