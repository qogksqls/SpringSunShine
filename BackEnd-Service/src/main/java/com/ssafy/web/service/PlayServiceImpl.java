package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.play.Play;
import com.ssafy.web.db.repository.PlayRepository;
import com.ssafy.web.model.response.PlayResponse;
import com.ssafy.web.request.PlayRequest;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayRepository playRepository;

	@Autowired
	WebClient webClient;

	/** 보호자 -> 아동의 놀이 기록 조회 */
	@Override
	public List<PlayResponse> getChildPlaylist(String childId) {
		List<Play> list = playRepository.findByChildId(childId);
		List<PlayResponse> playList = new ArrayList<PlayResponse>();
		
		for(Play play: list) {
			String childName = webClient.get().uri("/info/child/" + childId).retrieve().bodyToMono(String.class)
					.block();
			
			PlayResponse pResponse = new PlayResponse();
			
			pResponse.setChildId(childId);
			pResponse.setChildName(childName);
			pResponse.setPlayTime(play.getCreateTime());
			pResponse.setTotalTime(play.getTotalTime());
			pResponse.setScore(play.getScore());
			
			playList.add(pResponse);
		}
		
		return playList;
	}

	/** 보호자 -> 아동의 놀이 기록 저장 */
	@Override
	public void savePlayResult(PlayRequest playRequest) {
		Play play = new Play();
		
		play.setChildId(playRequest.getChildId());
		play.setCreateTime(playRequest.getCreateTime());
		play.setScore(playRequest.getScore());
		play.setTotalTime(playRequest.getTotalTime());
		
		playRepository.save(play);
	}

}
