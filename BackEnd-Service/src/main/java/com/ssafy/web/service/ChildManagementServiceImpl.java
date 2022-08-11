package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.model.response.ChildResponse;

@Service
public class ChildManagementServiceImpl implements ChildManagementService {

	@Autowired
	WebClient webClient;

	/** 아동 목록 조회 */
	@Override
	public List<ChildResponse> getChildList(String parentId) {
		List<ChildResponse> childList = webClient.get().uri("/child/" + parentId).retrieve()
				.bodyToFlux(ChildResponse.class).collectList().block();
		return childList;
	}

}
