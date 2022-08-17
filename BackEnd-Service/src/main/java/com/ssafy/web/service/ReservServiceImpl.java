package com.ssafy.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.Reservation;
import com.ssafy.web.db.repository.ReservRepository;
import com.ssafy.web.model.response.ParentReservResponse;
import com.ssafy.web.model.response.TheraReservResponse;
import com.ssafy.web.request.ReservRequest;

@Service
public class ReservServiceImpl implements ReservService {

	@Autowired
	ReservRepository reservRepository;

	@Autowired
	WebClient webClient;

	/** 보호자 -> 상담 예약하기 */
	@Override
	public void reservRegist(ReservRequest reservInfo) {
		Reservation reserv = new Reservation();

		// auth-api에서 아동 아이디 가져오기
		String childName = reservInfo.getChild_name();
		String parentId = reservInfo.getParent_id();

		String childId = webClient.get().uri("/child/" + parentId + "/" + childName).retrieve().bodyToMono(String.class)
				.block();
		System.out.println("아동 아이디: " + childId);

		reserv.setChildId(childId);
		reserv.setParentId(reservInfo.getParent_id());
		reserv.setTheraId(reservInfo.getThera_id());
		reserv.setReservTime(reservInfo.getReserv_time());

		reservRepository.save(reserv);
	}

	/** 보호자가 예약한 상담 리스트 조회 */
	@Override
	public List<ParentReservResponse> getReservByParent(String parentId) {
		List<Reservation> list = reservRepository.findByParentId(parentId);
		List<ParentReservResponse> reservList = new ArrayList<ParentReservResponse>();

		for (Reservation reserv : list) {
			String childId = reserv.getChildId();
			String theraId = reserv.getTheraId();
			Date reservTime = reserv.getReservTime();

			String childName = webClient.get().uri("/info/child/" + childId).retrieve().bodyToMono(String.class)
					.block();
			String theraName = webClient.get().uri("/info/thera/" + theraId).retrieve().bodyToMono(String.class)
					.block();

			ParentReservResponse pReservResponse = new ParentReservResponse();
			pReservResponse.setChildId(childId);
			pReservResponse.setChildName(childName);
			pReservResponse.setTheraId(theraId);
			pReservResponse.setTheraName(theraName);
			pReservResponse.setReservTime(reservTime);

			reservList.add(pReservResponse);
		}

		return reservList;
	}

	/** 보호자 + 아동 아이디로 예약한 상담 리스트 조회 */
	@Override
	public List<ParentReservResponse> getReservByParentAndChild(String parentId, String childId) {
		List<Reservation> list = reservRepository.findByParentIdAndChildId(parentId, childId);
		List<ParentReservResponse> reservList = new ArrayList<ParentReservResponse>();

		for (Reservation reserv : list) {
			String theraId = reserv.getTheraId();
			Date reservTime = reserv.getReservTime();

			String childName = webClient.get().uri("/info/child/" + childId).retrieve().bodyToMono(String.class)
					.block();
			String theraName = webClient.get().uri("/info/thera/" + theraId).retrieve().bodyToMono(String.class)
					.block();

			ParentReservResponse pReservResponse = new ParentReservResponse();
			pReservResponse.setChildId(childId);
			pReservResponse.setChildName(childName);
			pReservResponse.setTheraId(theraId);
			pReservResponse.setTheraName(theraName);
			pReservResponse.setReservTime(reservTime);

			reservList.add(pReservResponse);
		}
		return reservList;
	}

	/** 상담사에게 예약된 리스트 조회 */
	@Override
	public List<TheraReservResponse> getReservByThera(String theraId) {
		List<Reservation> list = reservRepository.findByTheraId(theraId);
//		System.out.println("상담사 테이블 조회");
		List<TheraReservResponse> reservList = new ArrayList<TheraReservResponse>();

		for (Reservation reserv : list) {
			String childId = reserv.getChildId();
			String parentId = reserv.getParentId();
			Date reservTime = reserv.getReservTime();

			String childName = webClient.get().uri("/info/child/" + childId).retrieve().bodyToMono(String.class)
					.block();
			String parentName = webClient.get().uri("/info/parent/" + parentId).retrieve().bodyToMono(String.class)
					.block();

			TheraReservResponse tReservResponse = new TheraReservResponse();
			tReservResponse.setChildId(childId);
			tReservResponse.setChildName(childName);
			tReservResponse.setParentName(parentName);
			tReservResponse.setParentId(parentId);
			tReservResponse.setReservTime(reservTime);

			reservList.add(tReservResponse);
		}

		return reservList;
	}

	@Override
	public List<TheraReservResponse> getReservByTheraAndChild(String theraId, String childId) {
		List<Reservation> list = reservRepository.findByTheraIdAndChildId(theraId, childId);
		List<TheraReservResponse> reservList = new ArrayList<TheraReservResponse>();
		
		for (Reservation reserv : list) {
			String parentId = reserv.getParentId();
			Date reservTime = reserv.getReservTime();
System.out.println(parentId);
			String childName = webClient.get().uri("/info/child/" + childId).retrieve().bodyToMono(String.class)
					.block();
			String parentName = webClient.get().uri("/info/parent/" + parentId).retrieve().bodyToMono(String.class)
					.block();

			TheraReservResponse tReservResponse = new TheraReservResponse();
			tReservResponse.setChildId(childId);
			tReservResponse.setChildName(childName);
			tReservResponse.setParentName(parentName);
			tReservResponse.setParentId(parentId);
			tReservResponse.setReservTime(reservTime);

			reservList.add(tReservResponse);
		}

		return reservList;
	}

}
