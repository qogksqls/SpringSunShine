package com.ssafy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.web.db.repository.BExpertiseTherapistRepository;
import com.ssafy.web.model.response.BExpertiseTherapistMapping;
import com.ssafy.web.model.response.RecommendTherapistResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BExpertiseTherapistServiceImpl implements BExpertiseTherapistService{
	private final BExpertiseTherapistRepository BETRepo;
	
	@Override
	public List<RecommendTherapistResponse> recommendTherapistList(int expertise_no) {
		List<BExpertiseTherapistMapping> list = BETRepo.findByExpertiseNo(expertise_no);
		System.out.println(list.size());
		return null;
	}
}
