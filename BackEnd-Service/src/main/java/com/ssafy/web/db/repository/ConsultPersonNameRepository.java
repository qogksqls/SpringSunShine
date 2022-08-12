package com.ssafy.web.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.ConsultPersonName;

public interface ConsultPersonNameRepository extends JpaRepository<ConsultPersonName, Integer> {
	ConsultPersonName findByConsult_ConsultNo(int consultNo);
}
