package com.ssafy.web.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.RandomUserId;
import com.ssafy.web.db.entity.Child;
import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.ChildRepository;
import com.ssafy.web.db.repository.ParentRepository;
import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.dto.ChildData;
import com.ssafy.web.model.response.ChildResponse;
import com.ssafy.web.request.ChildRegisterRequest;

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
		child.setSurveyFlag(childInfo.getSurvey_flag());

		childRepository.save(child);
	}

	/** 아동 목록 조회 */
	@Override
	public List<ChildResponse> getChildList(String parentId) {
		User user = userRepository.findByUserId(parentId);
		Parent parent = parentRepository.findByUser(user);

		List<Child> list = childRepository.findByParent(parent);
		List<ChildResponse> childList = new ArrayList<ChildResponse>();
		for(Child child : list) {
			ChildResponse childResponse = new ChildResponse();
			
			childResponse.setChildId(child.getChildId());
			childResponse.setName(child.getName());
			childResponse.setBirth(child.getBirth());
			childResponse.setGender(child.getGender());
			childResponse.setProfileUrl(child.getProfileUrl());
			childResponse.setSurveyFlag(child.getSurveyFlag());
			
			childList.add(childResponse);
		}
		
		return childList;
	}

	/** 아동 아이디 찾기 */
	@Override
	public String getChildId(String parentId, String childName) {
		User user = userRepository.findByUserId(parentId);
		Parent parent = parentRepository.findByUser(user);

		Child child = childRepository.findByParentAndName(parent, childName);

		return child.getChildId();
	}

	/** 아동 이름 반환 */
	@Override
	public String getChildName(String childId) {
		Child child = childRepository.findByChildId(childId);
		return child.getName();
	}

	/**아동 성별, 나이  조회 */
	@Override
	public ChildData getChildData(String child_id) {
		Child child = childRepository.findByChildId(child_id);
		String gender = child.getGender();//아이 성별 
		// 아이가 태어난 년도 
		String date = child.getBirth().toString().substring(0,4);
		
		 // 현재 년도 
		LocalDate now = LocalDate.now();
		String no = now.toString().substring(0,4);
		
		int age= Integer.parseInt(no)-Integer.parseInt(date) + 1 ;//현재 나이 
		
		ChildData data  = new ChildData();
		data.setAge(age);
		data.setGender(gender);
		
		return data;
	
	}
	
	
	

}
