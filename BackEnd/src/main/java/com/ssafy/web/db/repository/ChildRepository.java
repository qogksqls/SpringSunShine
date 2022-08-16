package com.ssafy.web.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Child;
import com.ssafy.web.db.entity.Parent;

public interface ChildRepository extends JpaRepository<Child, Integer> {

	Child findByChildId(String childId);
    
	List<Child> findByParent(Parent parent);
	
	/** 보호자 아이디와 아동 이름으로 child_id 반환 */ 
	Child findByParentAndName(Parent parent, String childName);
	
 
	
}