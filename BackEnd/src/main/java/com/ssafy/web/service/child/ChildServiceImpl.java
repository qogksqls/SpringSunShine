package com.ssafy.web.service.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.RandomUserId;
import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.entity.child.Child;
import com.ssafy.web.db.repository.ChildRepository;
import com.ssafy.web.db.repository.ParentRepository;
import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.request.child.ChildRegisterRequest;

@Service
public class ChildServiceImpl implements ChildService {

	@Autowired
	ChildRepository childRepository;
	@Autowired
	ParentRepository parentRepository;
	@Autowired
	UserRepository userRepository;

	/** 아동 등록 */
	@Override
	public void childRegist(ChildRegisterRequest childInfo) {
		Child child = new Child();

		User user = userRepository.findByUserId(childInfo.getParent_id());
		Parent parent = parentRepository.findByUser(user);
		child.setChildId(RandomUserId.makeChildId());
		child.setParent(parent);
		child.setName(childInfo.getName());
		child.setBirth(childInfo.getBirth());
		child.setGender(childInfo.getGender());
		child.setProfileUrl(childInfo.getProfile_url());

		childRepository.save(child);
	}

	@Override
	public String getChildId(String parentId, String childName) {
		User user = userRepository.findByUserId(parentId);
		Parent parent = parentRepository.findByUser(user);
		
		Child child = childRepository.findByParentAndChildId(parent, childName);

		return child.getChildId();
	}

}

