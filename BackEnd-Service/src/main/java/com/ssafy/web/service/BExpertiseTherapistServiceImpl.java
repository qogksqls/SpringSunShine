package com.ssafy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.repository.BExpertiseTherapistRepository;
import com.ssafy.web.model.response.BExpertiseTherapistMapping;
import com.ssafy.web.model.response.RecommendTherapistMapping;
import com.ssafy.web.model.response.RecommendTherapistResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BExpertiseTherapistServiceImpl implements BExpertiseTherapistService {
	private final BExpertiseTherapistRepository BETRepo;
	private final WebClient webClient;

	@Override
	public List<RecommendTherapistResponse> recommendTherapistList(int expertise_no) {
//		List<String> betmList = BETRepo.findByExpertiseNo(expertise_no);
//		String apiList = webClient.post().uri("/therapist/recommed").syncBody(betmList).retrieve()
//				.bodyToMono(String.class).block();
//		System.out.println(apiList);
//		System.out.println(betmList);
		return null;
	}
}
