package com.ssafy.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.Expertise;
import com.ssafy.web.db.repository.ExpertiseRepository;
import com.ssafy.web.model.response.ExpertiseResponse;
import com.ssafy.web.model.response.RecommendTherapistResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpertiseServiceImpl implements ExpertiseService {
	private final ExpertiseRepository expertiseRep;

	@Override
	public List<ExpertiseResponse> expertiseFindAll() {
		System.out.println(333333);
		return expertiseRep.findAll().stream().map(expertise -> ExpertiseResponse.fromEntity(expertise))
				.collect(Collectors.toList());
	}

}
