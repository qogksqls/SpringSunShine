package com.ssafy.web.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.response.ConsultResponse;
import com.ssafy.web.model.response.ConsultTotalResponse;
import com.ssafy.web.request.ConsultRequest;
import com.ssafy.web.service.ConsultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("consult")
public class ConsultController {
	private final ConsultService conService;

	/* 상담방 생성 */
	@PostMapping("/room")
	public int creatRoom(@RequestBody ConsultRequest conreq) {
		int consultNo = conService.createRoom(conreq);
		return consultNo;
	}
	
	/*메모 수정*/
	@PutMapping("/memo")
	public String updateMemo(@RequestBody ConsultRequest conreq) {
		conService.updateMemo(conreq);
		return "success";
	}
	
	/*일지 수정*/
	@PutMapping("/record")
	public String updateRecord(@RequestBody ConsultRequest conreq) {
		conService.updateMemo(conreq);
		return "success";
	}
	
	/* 해당 치료사가 자신이 맡은 모든 아동의 기록을 보고 싶은 경우 */
	@GetMapping("/thearpist/{theraId}/{page}/{size}")
	public List<ConsultTotalResponse> findByTherapist(@PathVariable("theraId") String theraId,
			@PathVariable("page") int page, @PathVariable("size") int size){
		PageRequest pr = PageRequest.of(page-1, size);
		List<ConsultTotalResponse> list = conService.findByTheraId(theraId, pr);
		return list;
	}
	
	/* 해당 치료사가 원하는 아이의 상담 기록만 보고 싶은 경우 */
	@GetMapping("/thearpist/{theraId}/{childId}/{page}/{size}")
	public List<ConsultTotalResponse> findByTherapistAndChildId(
			@PathVariable("theraId") String theraId, @PathVariable("childId") String childId,
			@PathVariable("page") int page, @PathVariable("size") int size){
		PageRequest pr = PageRequest.of(page-1, size);
		List<ConsultTotalResponse> list = conService.findByTheraIdAndChildId(theraId,childId, pr);
		return list;
	}

	/* 해당 부모가 해당 아이의 상담 기록만 보고 싶은 경우 */
	@GetMapping("/parent/{parentId}/{childId}/{page}/{size}")
	public List<ConsultTotalResponse> findConsultByChildId(
			@PathVariable("parentId") String parentId ,@PathVariable("childId") String childId,
			@PathVariable("page") int page, @PathVariable("size") int size){
		PageRequest pr = PageRequest.of(page-1, size);
		List<ConsultTotalResponse> list = conService.findByChildId(childId, pr);
		return list;
	}
	
}
