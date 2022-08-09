package com.ssafy.web.service;

import java.util.List;

import com.ssafy.web.db.entity.Reservation;
import com.ssafy.web.request.ReservRequest;

public interface ReservService {

	// 보호자 -> 상담사 상담 예약
	void reservRegist(ReservRequest reservInfo);

	// 상담사 -> 예약된 날짜 가져오기
	List<Reservation> getReservByThera(String theraId);
}
