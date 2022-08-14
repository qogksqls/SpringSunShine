package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.model.response.PlayResponse;
import com.ssafy.web.request.PlayRequest;

public interface PlayService {

	// 보호자 -> 아동의 놀이 기록 조회
	List<PlayResponse> getChildPlaylist(String childId);
	
	// 보호자 -> 아동의 놀이 기록 저장
	void savePlayResult(PlayRequest playRequest);
}
