package com.ssafy.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.web.RandomUserId;
import com.ssafy.web.common.PathUtil;
import com.ssafy.web.db.entity.Child;
import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.ChildRepository;
import com.ssafy.web.db.repository.ParentRepository;
import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.dto.ChildData;
import com.ssafy.web.model.response.ChildReservResponse;
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

	@Autowired
	ServletContext servletContext;

	/** 아동 등록 */
	@Override
	public void childRegist(MultipartFile profile, ChildRegisterRequest childInfo) {
		Child child = new Child();

		User user = userRepository.findByUserId(childInfo.getParent_id());
		Parent parent = parentRepository.findByUser(user);
		child.setChildId(RandomUserId.makeChildId());
		child.setParent(parent);
		child.setName(childInfo.getName());
		child.setBirth(childInfo.getBirth());
		child.setGender(childInfo.getGender());
		child.setSurveyFlag(childInfo.getSurvey_flag());

//		child.setProfileUrl(childInfo.getProfile_url());
		if (profile != null && !"".equals(profile.getOriginalFilename())) {

//			String fileName = child.getChildId() + profile.getOriginalFilename();
//			String url = ClassLoader.getSystemClassLoader().getResource(".").getPath()+PathUtil.PROFILE_UPLOAD_PATH+fileName;
			
			String fileName = user.getUserId() + profile.getOriginalFilename();
			String url = PathUtil.PROFILE_UPLOAD_PATH+ fileName;
			try {
				profile.transferTo(new File(url));
				child.setProfileUrl(fileName);
				
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

		} else {
			child.setProfileUrl(null);
		}

		childRepository.save(child);
	}

	/** 아동 목록 조회 */
	@Override
	public List<ChildResponse> getChildList(String parentId) throws IOException {
		User user = userRepository.findByUserId(parentId);
		Parent parent = parentRepository.findByUser(user);

		List<Child> list = childRepository.findByParent(parent);
		List<ChildResponse> childList = new ArrayList<ChildResponse>();
		for (Child child : list) {
			ChildResponse childResponse = new ChildResponse();

			childResponse.setChildId(child.getChildId());
			childResponse.setName(child.getName());
			childResponse.setBirth(child.getBirth());
			childResponse.setGender(child.getGender());
//			childResponse.setProfileUrl(child.getProfileUrl());

			if (child.getProfileUrl() == null) {
				childResponse.setProfileUrl(null);
			} else {
				
//				InputStream resourceAsStream = this.getClass().getResourceAsStream(PathUtil.PROFILE_PATH+child.getProfileUrl());
//				byte[] imageByteArray = IOUtils.toByteArray(resourceAsStream);
//				childResponse.setProfileUrl(imageByteArray);
//				resourceAsStream.close();
				
				String url = PathUtil.PROFILE_UPLOAD_PATH+ child.getProfileUrl();
				InputStream imageIS = new FileInputStream(url);
				byte[] imageByteArray = IOUtils.toByteArray(imageIS);
				childResponse.setProfileUrl(imageByteArray);
				imageIS.close();

			}

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

	/** 아동 성별, 나이 조회 */
	@Override
	public ChildData getChildData(String child_id) {
		Child child = childRepository.findByChildId(child_id);
		String gender = child.getGender();// 아이 성별
		// 아이가 태어난 년도
		String date = child.getBirth().toString().substring(0, 4);

		// 현재 년도
		LocalDate now = LocalDate.now();
		String no = now.toString().substring(0, 4);

		int age = Integer.parseInt(no) - Integer.parseInt(date) + 1;// 현재 나이

		ChildData data = new ChildData();
		data.setAge(age);
		data.setGender(gender);

		return data;

	}

	@Override
	@Transactional
	public int surveyFlag(String child_id) {
		Child child = childRepository.findByChildId(child_id);
		if (child == null)
			return 0;
		int flag = child.getSurveyFlag();
		if (flag == 1)
			return 0; // 이미 응답한 아동

		child.update(1);
		return 1;

	}

	/** 상담사 -> 예약한 아동 정보 조회 */
	@Override
	public ChildReservResponse getChildInfo(String childId) throws IOException {
		Child child = childRepository.findByChildId(childId);
		ChildReservResponse childInfo = new ChildReservResponse();

		System.out.println("예약한 아동 정보 조회");
		childInfo.setChildId(childId);
		childInfo.setName(child.getName());
		childInfo.setBirth(child.getBirth());
		childInfo.setGender(child.getGender());
//		childInfo.setProfileUrl(child.getProfileUrl());

		if (child.getProfileUrl() == null) {
			childInfo.setProfileUrl(null);
		} else {
//			InputStream resourceAsStream = this.getClass().getResourceAsStream(PathUtil.PROFILE_PATH+child.getProfileUrl());
//			byte[] imageByteArray = IOUtils.toByteArray(resourceAsStream);
//			childInfo.setProfileUrl(imageByteArray);
//			resourceAsStream.close();
			String url = PathUtil.PROFILE_UPLOAD_PATH+ child.getProfileUrl();
			InputStream imageIS = new FileInputStream(url);
			byte[] imageByteArray = IOUtils.toByteArray(imageIS);
			childInfo.setProfileUrl(imageByteArray);
			imageIS.close();
			
		}

		childInfo.setSurveyFlag(child.getSurveyFlag());
		childInfo.setParentName(child.getParent().getName());
		childInfo.setParentId(child.getParent().getUser().getUserId());
		childInfo.setParentPhone(child.getParent().getPhone());

		return childInfo;
	}

}
