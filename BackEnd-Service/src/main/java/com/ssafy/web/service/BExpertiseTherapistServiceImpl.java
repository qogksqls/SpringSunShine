package com.ssafy.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.repository.BExpertiseTherapistRepository;
import com.ssafy.web.model.response.RecommendTherapistResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BExpertiseTherapistServiceImpl implements BExpertiseTherapistService {
	private final BExpertiseTherapistRepository BETRepo;
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

}
