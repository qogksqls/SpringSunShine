package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.web.db.entity.Reservation;
import com.ssafy.web.db.repository.ReservRepository;
import com.ssafy.web.request.ReservRequest;

@Service
public class ReservServiceImpl implements ReservService {

	@Autowired
	ReservRepository reservRepository;

	@Autowired
	WebClient webClient;

	@Override
	public void reservRegist(ReservRequest reservInfo) {
		Reservation reserv = new Reservation();
// git 충돌 해결???
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

	@Override
	public List<Reservation> getReservByThera(String theraId) {
		return reservRepository.selectAllSQL(theraId);
	}

}
