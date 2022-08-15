package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.dto.ObjectDto;
import com.ssafy.web.model.response.BaseResponseBody;
import com.ssafy.web.model.response.PlayResponse;
import com.ssafy.web.request.PlayRequest;
import com.ssafy.web.service.PlayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "놀이 API", tags = { "Play" })
@RequestMapping("/play")
public class PlayController {

	@Autowired
	PlayService playService;
	
	@GetMapping("/objectcard")
	@ApiOperation(value = "사물 카드 목록 조회")
	public List<ObjectDto> getObjectCardList(){
		return playService.objectCardPlay();
	}

	@GetMapping("/list/{child_id}")
	@ApiOperation(value = "아동 놀이 기록 조회")
	public List<PlayResponse> getChildPlaylist(@PathVariable(value = "child_id") String childId) {
		return playService.getChildPlaylist(childId);
	}
	
	@PostMapping("/result")
	@ApiOperation(value = "아동 놀이 기록 저장")
	public ResponseEntity<?> savePlayResult(@RequestBody PlayRequest playRequest){
		playService.savePlayResult(playRequest);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
	}

}
