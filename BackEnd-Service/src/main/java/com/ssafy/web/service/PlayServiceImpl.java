package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.play.ObjectCard;
import com.ssafy.web.db.entity.play.Play;
import com.ssafy.web.db.repository.ObjectCardRepository;
import com.ssafy.web.db.repository.PlayRepository;
import com.ssafy.web.dto.ObjectDto;
import com.ssafy.web.model.response.PlayResponse;
import com.ssafy.web.request.PlayRequest;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayRepository playRepository;
	
	@Autowired
	ObjectCardRepository objcetCardRepository;

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

	/** 사물 카드 놀이 3장씩 카드 보내기 */
	@Override
	public List<ObjectDto> objectCardPlay() {
		int totalCard = objcetCardRepository.findAll().size();
		int arr[] = new int[3]; // 카드 아이디 3개 저장
		
		Random random = new Random();
		for(int i=0; i<3; i++) {
			arr[i] = random.nextInt(totalCard)+1;
			for(int j=0; j<i; j++) {
				if(arr[i]  == arr[j]) {
					i--;
				}
			}
		}
		
		System.out.printf("카드 아이디: %d %d %d",arr[0], arr[1], arr[2]);
	
		List<ObjectDto> objectList = new ArrayList<ObjectDto>();
		for(int i=0; i<3; i++) {
			ObjectCard card = objcetCardRepository.findByCardId(arr[i]);
			ObjectDto objectCard = new ObjectDto();
			
			objectCard.setImage(card.getImage());
			objectCard.setName(card.getName());
			objectCard.setQuestion(card.getQuestion());
			
			objectList.add(objectCard);
		}
		
		return objectList;
	}

}
