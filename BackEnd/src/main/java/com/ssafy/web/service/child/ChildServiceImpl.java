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
		// parent_no 외래키
//		int parent_no;
//		child.setParent_no(parent.getParent_no());
//		child.setName(childInfo.getName());
//		child.setBirth(childInfo.getBirth());
//		child.setGender(childInfo.getGender());
//		user.setUser_id(childInfo.getParent_id());
//		Parent parent = parentRepository.findByUser(user).orElseThrow(() -> new RuntimeException());
//		Parent parent = Parent.builder().user(user);

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

	/** 아동 삭제 */
	@Override
	public void childDelete(String childId) {
		Child child = childRepository.findByChildId(childId);

		childRepository.delete(child);
	}

	@Override
	public String getChildId(String childName, String parentId) {
		Child child = childRepository.findByChildNameAndParentId(childName, parentId);

		return child.getChildId();
	}

}
