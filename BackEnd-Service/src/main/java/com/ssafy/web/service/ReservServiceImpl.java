package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.Reservation;
import com.ssafy.web.db.repository.ReservRepository;
import com.ssafy.web.request.ReservRequest;

@Service
public class ReservServiceImpl implements ReservService {

	@Autowired
	ReservRepository reservRepository;

	@Override
	public void reservRegist(ReservRequest reservInfo) {
		Reservation reserv = new Reservation();

//		reserv.setChildId(reservInfo.getChild_name());
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
