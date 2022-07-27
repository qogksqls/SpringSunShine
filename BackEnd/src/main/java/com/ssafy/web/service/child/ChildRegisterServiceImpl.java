package com.ssafy.web.service.child;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.Child;
import com.ssafy.web.db.repository.ChildRepository;
import com.ssafy.web.request.child.ChildRegisterRequest;

@Service
public class ChildRegisterServiceImpl implements ChildRegisterService {

	@Autowired
	ChildRepository childRepository;

	// 아이 등록
	@Override
	public void childRegist(ChildRegisterRequest childInfo) {
		// parent_no 외래키
		int parent_no = 0;

		String name = childInfo.getName();
		Date birth = childInfo.getBirth();
		int gender = childInfo.getGender();

		Child child = Child.registerChild(parent_no, name, birth, gender);
		childRepository.save(child);
	}

}
