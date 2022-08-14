package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.model.response.ChildReservResponse;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;

import reactor.core.publisher.Mono;

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

	/** 아동 등록 */
	@Override
	public void childRegist(ChildRegisterRequest childInfo) {
//		System.out.println(childInfo.getName());

		webClient.post().uri("/child/register").bodyValue(childInfo).retrieve().bodyToMono(String.class).block();

	}

	/** 상담사 -> 예약한 아동 정보 조회 */
	@Override
	public ChildReservResponse getChildInfo(String childId) {
		ChildReservResponse childInfo = webClient.get().uri("/reserv-therapist-child/" + childId).retrieve()
				.bodyToMono(ChildReservResponse.class).block();

		return childInfo;
	}

}
