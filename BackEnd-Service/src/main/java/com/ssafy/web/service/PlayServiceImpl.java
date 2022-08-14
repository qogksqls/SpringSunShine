package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.play.Play;
import com.ssafy.web.db.repository.PlayRepository;
import com.ssafy.web.model.response.PlayResponse;

public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayRepository playRepository;

	@Autowired
	WebClient webClient;

	@Override
	public List<PlayResponse> getChildPlaylist(String childId) {
		List<Play> list = playRepository.findByChildId(childId);
		List<PlayResponse> playList = new ArrayList<PlayResponse>();
		
		return playList;
	}

}
