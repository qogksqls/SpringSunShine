package com.ssafy.web.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.web.model.response.RecommendTherapistResponse;
import com.ssafy.web.service.RecommendService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/therapist")
public class RecommendTherapistController {
	private final RecommendService recoService;
	
//	@PostMapping("/recommed")
//	public List<RecommendTherapistResponse> recommendTherapist(HttpServletRequest req) throws IOException {
//		ServletInputStream inputStream = req.getInputStream();
//		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
////	    System.out.println(messageBody);
//	    
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    String[] strArr = objectMapper.readValue(messageBody, String[].class);
//        
//		List<RecommendTherapistResponse> list = recoService.findByUser_UserIdIn(strArr);
//		return list;
//	}
	
	@GetMapping("/recommed")
	public List<RecommendTherapistResponse> recommendTherapist(@RequestParam String[] betmList){
		List<RecommendTherapistResponse> list = recoService.findByUser_UserIdIn(betmList);
		return list;
	}
	
	@GetMapping("/recommedall")
	public List<RecommendTherapistResponse> recommendTherapistAll(){
		List<RecommendTherapistResponse> list = recoService.findAlljpql();
		return list;
	}

}
