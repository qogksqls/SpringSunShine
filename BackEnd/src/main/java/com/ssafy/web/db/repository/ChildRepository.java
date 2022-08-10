package com.ssafy.web.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.child.Child;

public interface ChildRepository extends JpaRepository<Child, Integer> {

	Child findByChildId(String childId);
    
	/** 보호자 아이디와 아동 이름으로 child_id 반환 */ 
	Child findByParentAndChildId(Parent parent, String childName);
 
}