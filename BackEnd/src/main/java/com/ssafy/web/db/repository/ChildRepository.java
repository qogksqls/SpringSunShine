package com.ssafy.web.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.web.db.entity.child.Child;

public interface ChildRepository extends JpaRepository<Child, Integer> {

	Child findByChildId(String childId);

	/** child_id 반환 */
	Child findByChildNameAndParentId(String childName, String parentId);

}
