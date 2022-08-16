package com.ssafy.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.model.response.ChildReservResponse;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;

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
	public void childRegist(MultipartFile profile, ChildRegisterRequest childInfo) {
//		System.out.println(childInfo.getName());

//		webClient.post().uri("/child/register").bodyValue(childInfo).retrieve().bodyToMono(String.class).block();

		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		multiValueMap.add("childInfo", childInfo);
		try {
			Resource r = new FileSystemResource(profile.getBytes(), profile.getOriginalFilename());
			multiValueMap.add("profile", r);
		} catch (IOException e) {
			e.printStackTrace();
		}

		webClient.post().uri("/child/register").contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.APPLICATION_JSON).bodyValue(multiValueMap).retrieve().bodyToMono(String.class)
				.block();

	}

	public static class FileSystemResource extends ByteArrayResource {

		private String fileName;

		public FileSystemResource(byte[] byteArray, String filename) {
			super(byteArray);
			this.fileName = filename;
		}

		public String getFilename() {
			return fileName;
		}

		public void setFilename(String fileName) {
			this.fileName = fileName;
		}
	}

	/** 상담사 -> 예약한 아동 정보 조회 */
	@Override
	public ChildReservResponse getChildInfo(String childId) {
		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
			     .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
			     .build();
		
		ChildReservResponse childInfo = webClient.get().uri("/child/reserv-therapist-child/" + childId).retrieve()
				.bodyToMono(ChildReservResponse.class).block();

		return childInfo;
	}

}
