package com.ssafy.web.service.child;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.Child;
import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.repository.ChildRepository;
import com.ssafy.web.request.child.ChildRegisterRequest;

@Service
public class ChildRegisterServiceImpl implements ChildRegisterService {

	@Autowired
	ChildRepository childRepository;

	// 아이 등록
	@Override
	public void childRegist(ChildRegisterRequest childInfo, Parent parent) {
		Child child = new Child();

		// parent_no 외래키
//		int parent_no;
		child.setParent_no(parent.getParent_no());
		child.setName(childInfo.getName());
		child.setBirth(childInfo.getBirth());
		child.setGender(childInfo.getGender());

		childRepository.save(child);
	}

}
