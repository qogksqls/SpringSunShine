package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.BExpertiseChild;
import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.repository.BExpertiseChildRepository;
import com.ssafy.web.db.repository.BExpertiseTherapistRepository;
import com.ssafy.web.db.repository.ExpertiseRepository;
import com.ssafy.web.model.response.RecommendTherapistResponse;
import com.ssafy.web.model.response.RecommendTherapistTotalResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BExpertiseTherapistServiceImpl implements BExpertiseTherapistService {
	private final BExpertiseTherapistRepository BETRepo;
	private final BExpertiseChildRepository BECRepo;
	private final ExpertiseRepository expertiseRep;
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
	public List<RecommendTherapistTotalResponse> recommendTherapistList(int expertise_no) {
		List<String> betmList = BETRepo.findByExpertiseNo(expertise_no);
		RecommendTherapistResponse[] apiList = 
				webClient.get()
				.uri(builder -> builder.path("/therapist/recommed/")
                        .queryParam("betmList",betmList)
                        .build())
				.retrieve()
				.bodyToMono(RecommendTherapistResponse[].class).block();
		
		List<RecommendTherapistTotalResponse> totalList = new ArrayList<>();
		Arrays.stream(apiList).forEach(e->{
			List<Expertise> list = expertiseRep.findByTheraIdjpql(e.getThera_id());
			totalList.add(new RecommendTherapistTotalResponse(e, list));
		});
		
		return totalList;
	}

	@Override
	public List<RecommendTherapistTotalResponse> recommendTherapistAll() {
		RecommendTherapistResponse[] apiList = 
				webClient.get()
				.uri("/therapist/recommedall")
				.retrieve()
				.bodyToMono(RecommendTherapistResponse[].class).block();

		List<RecommendTherapistTotalResponse> totalList = new ArrayList<>();
		Arrays.stream(apiList).forEach(e->{
			List<Expertise> list = expertiseRep.findByTheraIdjpql(e.getThera_id());
			totalList.add(new RecommendTherapistTotalResponse(e, list));
		});
		
		return totalList;
	}

	//아동의 증상번호 불러오기
	@Override
	public int getChildExp(String child_id) {
		BExpertiseChild childEx = BECRepo.findByChildId(child_id);
		int expno = childEx.getExpertise().getExpertiseNo();
		System.out.println(child_id+"의 증상 : "+expno);
		return expno;
	}

}
