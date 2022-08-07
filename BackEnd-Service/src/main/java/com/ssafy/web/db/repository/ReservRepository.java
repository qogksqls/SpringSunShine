package com.ssafy.web.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Reservation;

public interface ReservRepository extends JpaRepository<Reservation, Integer> {

}
