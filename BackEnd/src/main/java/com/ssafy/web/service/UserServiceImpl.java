package com.ssafy.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.web.RandomUserId;
import com.ssafy.web.common.PathUtil;
import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.ParentRepository;
import com.ssafy.web.db.repository.TheraRepository;
import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.dto.Academy;
import com.ssafy.web.dto.Career;
import com.ssafy.web.dto.Licence;
import com.ssafy.web.model.response.ParentResponse;
import com.ssafy.web.model.response.TherapistResponse;
import com.ssafy.web.request.ParentModifyRequest;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraModifyRequest;
import com.ssafy.web.request.TheraRegisterRequest;

import io.swagger.models.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	TheraRepository theraRepository;
	@Autowired
	ParentRepository parentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MailService mailService;

	@Autowired
	ServletContext servletContext;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	// 치료사 회원가입
	@Override
	public String theraRegist(MultipartFile profile, TheraRegisterRequest theraInfo) {
		try {
			User user = new User();
			user.setUserId(RandomUserId.makeTheraId());
			user.setId(theraInfo.getId());
			user.setPassword(encoder.encode(theraInfo.getPassword()));

			Therapist thera = new Therapist();
			thera.setName(theraInfo.getName());
			thera.setEmail(theraInfo.getEmail());
			thera.setPhone(theraInfo.getPhone());
			thera.setAddress(theraInfo.getAddress());
			if (theraInfo.getThera_intro().isEmpty()) {
				thera.setTheraIntro(null);
			} else {
				thera.setTheraIntro(theraInfo.getThera_intro());
			}
			// 파일 넣기
			if (profile != null && !"".equals(profile.getOriginalFilename())) {

				String fileName = user.getUserId() + profile.getOriginalFilename();
				String url = ClassLoader.getSystemClassLoader().getResource(".").getPath()
						+ PathUtil.PROFILE_UPLOAD_PATH + fileName;

				profile.transferTo(new File(url));
				thera.setProfileUrl(fileName);

			} else {
				thera.setProfileUrl(null);
			}

			List<Academy> academy = theraInfo.getAcademicCareers();
			List<Career> career = theraInfo.getCareers();
			List<Licence> licence = theraInfo.getLicences();
			thera.setAcademicCareers(getAcademy(academy));
			thera.setCareers(getCareer(career));
			thera.setLicences(getLicence(licence));

			thera.setUser(user);
			theraRepository.save(thera);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

//		return user.getUserId();
		return "sds";

	}

	public String getAcademy(List<Academy> academy) {
		int size = academy.size(); // 몇개의 학력
		if (size == 0)
			return null;
		String str = "";
		for (int i = 0; i < size; i++) {
			str += "[";
			str += academy.get(i).getName() + ",";
			str += academy.get(i).getMajor() + ",";
			str += academy.get(i).getAdmin() + ",";
			str += academy.get(i).getGradu() + "] ";
		}
		return str;
	}

	public String getCareer(List<Career> career) {
		int size = career.size(); // 몇개의 학력
		if (size == 0)
			return null;
		String str = "";
		for (int i = 0; i < size; i++) {
			str += "[";
			str += career.get(i).getName() + ",";
			str += career.get(i).getLevel() + ",";
			str += career.get(i).getDate() + ",";
			str += career.get(i).getRole() + "] ";
		}
		return str;
	}

	public String getLicence(List<Licence> licence) {
		int size = licence.size(); // 몇개의 학력
		if (size == 0)
			return null;
		String str = "";
		for (int i = 0; i < size; i++) {
			str += "[";
			str += licence.get(i).getName() + ",";
			str += licence.get(i).getPlace() + ",";
			str += licence.get(i).getDate() + ",";
			str += licence.get(i).getFile() + "] ";
		}
		return str;
	}

	// 부모 회원가입
	@Override
	public void parentRegist(ParentRegisterRequest parentInfo) {
		User user = new User();
		user.setUserId(RandomUserId.makeParentId());
		user.setId(parentInfo.getId());
		user.setPassword(encoder.encode(parentInfo.getPassword()));

		Parent parent = new Parent();
		parent.setName(parentInfo.getName());
		parent.setEmail(parentInfo.getEmail());
		parent.setPhone(parentInfo.getPhone());
		parent.setAddress(parentInfo.getAddress());
		parent.setUser(user);
		parentRepository.save(parent);

	}

	// 아이디 중복검사
	@Override
	public int checkId(String id) {
		User user = userRepository.findUserById(id).orElse(null);

		if (user == null) {
			// 사용가능한 아이디
			return 1;
		}
		return 0;

	}

	// 이메일 중복검사
	@Override
	public int checkEmail(String myemail) {
		Parent p = parentRepository.findByEmail(myemail).orElse(null);
		Therapist t = theraRepository.findByEmail(myemail).orElse(null);
		if (p == null && t == null) { // 해당 이메일을 사용하는 유저가 없음
			log.debug("사용가능한 이메일");
			return 1;
		} else {
			log.debug("중복 이메일");
			return 0;
		}
	}

	// 부모 회원정보 조회
	@Override
	public ParentResponse getParentInfo(String user_id) {
		User u = userRepository.findByUserId(user_id);
		Parent p = parentRepository.findByUser(u);

		ParentResponse pr = new ParentResponse();

		String s = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("해당 토큰의 사용자 아이디 : " + s);
		// 해당 사용자의 아이디가 아님
		if (!u.getId().equals(s)) {
			pr.setId("wrong-token");
			return pr;
		}

		pr.setId(u.getId());
		pr.setName(p.getName());
		pr.setPhone(p.getPhone());
		pr.setEmail(p.getEmail());
		pr.setAddress(p.getAddress());

		return pr;
	}

	// 치료사 정보 조회
	@Override
	public TherapistResponse getTheraInfo(String user_id) throws IOException {
		User u = userRepository.findByUserId(user_id);
		Therapist t = theraRepository.findByUser(u);

		TherapistResponse tr = new TherapistResponse();

		String s = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("해당 토큰의 사용자 아이디 : " + s);
		// 해당 사용자의 아이디가 아님
		if (!u.getId().equals(s)) {
			tr.setId("wrong-token");
			return tr;
		}
		// -----------경력 불러와서 리스트 설정
		List<Academy> acalist = new ArrayList<Academy>();
		if (stringCheck(t.getAcademicCareers())) {
			String[] acaList = getString(t.getAcademicCareers()); // a,b,c,d+e,f,g,h
			for (int i = 0; i < acaList.length; i++) {
				StringTokenizer st = new StringTokenizer(acaList[i], ",");
				Academy academy = new Academy();
				academy.setName(st.nextToken());
				academy.setMajor(st.nextToken());
				academy.setAdmin(st.nextToken());
				academy.setGradu(st.nextToken());
				acalist.add(academy);
			}
		}
		tr.setAcademy(acalist);
		// ------------------------
		List<Career> carlist = new ArrayList<Career>();
		if (stringCheck(t.getCareers())) {
			String[] carList = getString(t.getCareers());
			for (int i = 0; i < carList.length; i++) {
				StringTokenizer st = new StringTokenizer(carList[i], ",");
				Career career = new Career();
				career.setName(st.nextToken());
				career.setLevel(st.nextToken());
				career.setDate(st.nextToken());
				career.setRole(st.nextToken());
				carlist.add(career);
			}
		}
		tr.setCareers(carlist);
		// -------------------------
		List<Licence> liclist = new ArrayList<Licence>();
		if (stringCheck(t.getLicences())) {
			String[] licList = getString(t.getLicences());
			for (int i = 0; i < licList.length; i++) {
				StringTokenizer st = new StringTokenizer(licList[i], ",");
				// [asdf,null,2022-08-23,220720_출결확인서_배한빈[서울_6반]-1.pdf]
				Licence licence = new Licence();
				licence.setName(st.nextToken());
				licence.setPlace(st.nextToken());
				licence.setDate(st.nextToken());
				licence.setFile(st.nextToken());
				liclist.add(licence);
			}
		}
		tr.setLicence(liclist);

		if (t.getProfileUrl() == null || "".equals(t.getProfileUrl())) {
			tr.setProfile_url(null);
		} else {
//			String str = servletContext.getRealPath(PathUtil.PROFILE_PATH);
//			String url = str+t.getProfileUrl();
//			String url = "/home/ubuntu/compose/jenkins/workspace/a606-ci-cd/BackEnd/src/main/webapp/"+PathUtil.PROFILE_PATH+t.getProfileUrl();
//			String sss = ClassLoader.getSystemClassLoader().getResource(".").getPath()+PathUtil.PROFILE_PATH+t.getProfileUrl();
//			System.out.println(sss);
//			System.out.println(url);
//			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/"+t.getProfileUrl());
//			InputStream inputStream = new ClassPathResource(t.getProfileUrl()).getInputStream();
//			InputStream imageIS = new FileInputStream(sss);
			InputStream resourceAsStream = this.getClass()
					.getResourceAsStream(PathUtil.PROFILE_PATH + t.getProfileUrl());
			byte[] imageByteArray = IOUtils.toByteArray(resourceAsStream);
			tr.setProfile_url(imageByteArray);
			resourceAsStream.close();

		}

		tr.setId(u.getId());
		tr.setName(t.getName());
		tr.setEmail(t.getEmail());
		tr.setPhone(t.getPhone());
		tr.setAddress(t.getAddress());
		if (stringCheck(t.getTheraIntro())) {
			tr.setThera_intro(t.getTheraIntro());
		} else {
			tr.setThera_intro("");
		}

		return tr;

	}

	// 약력 : 띄어쓰기 분리하고, [ ] 빼는 과정
	public String[] getString(String data) {
		String[] list = data.split(" ");
		String[] res = new String[list.length];
		for (int i = 0; i < list.length; i++) {
			res[i] = list[i].replace("[", "").replace("]", "");
		}
		return res;
	}

	public boolean stringCheck(String data) {
		if (data != null) {
			return true;
		}
		return false;
	}

	// 부모 회원정보 수정
	@Override
	@Transactional
	public int parentModify(String user_id, ParentModifyRequest parentInfo) {
		User user = userRepository.findByUserId(user_id);
		if (user == null)
			return 0;
		String s = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("해당 토큰의 사용자 아이디 : " + s);
		// 해당 사용자의 아이디가 아님
		if (!user.getId().equals(s)) {
			return 2;
		}
		// 정보를 수정하려는 부모 회원
		Parent parent = parentRepository.findByUser(user);

		user.update(encoder.encode(parentInfo.getPassword()));
		parent.update(parentInfo.getName(), parentInfo.getPhone(), parentInfo.getAddress(), user);
		return 1;

	}

	// 치료사 회원정보 수정
	@Override
	@Transactional
	public int theraModify(String user_id, TheraModifyRequest theraInfo) {
		User user = userRepository.findByUserId(user_id);
		if (user == null)
			return 0;
		String s = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("해당 토큰의 사용자 아이디 : " + s);
		// 해당 사용자의 아이디가 아님
		if (!user.getId().equals(s)) {
			return 2;
		}
		// 정보수정하려는 치료사 회원
		Therapist thera = theraRepository.findByUser(user);
		user.update(encoder.encode(theraInfo.getPassword()));
		thera.update(theraInfo.getName(), theraInfo.getPhone(), theraInfo.getAddress(), theraInfo.getProfile_url(),
				theraInfo.getThera_intro(), user);
		return 1;
	}

	@Override
	@Transactional
	public int findPass(String id, String email) throws Exception {
		User user = userRepository.findUserById(id).orElse(null);
		if (user == null) {
			System.out.println("user--null");
			return 0; // id 오류
		}
		String s = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("해당 토큰의 사용자 아이디 : " + s);
		// 해당 사용자의 아이디가 아님
		if (!user.getId().equals(s)) {
			return 2;
		}
		Parent p = parentRepository.findByUser(user);
		if (p != null) { // 이사람은 부모
			if (!p.getEmail().equals(email)) {
				System.out.println("이메일없음");
				return 0;
			}
		} else { // 이사람은 치료사
			Therapist t = theraRepository.findByUser(user);
			if (!t.getEmail().equals(email))
				return 0;
		}
		log.debug("임시비밀번호 이메일 발송 및 업데이트");
		// 이메일 발송
		String code = mailService.sendPwMessage(email);
		// 임시 비밀번호로 비밀번호 업데이트
		user.update(encoder.encode(code));
		return 1;

	}

	/** 보호자 이름 반환 */
	@Override
	public String getParentName(String parentId) {
		User user = userRepository.findByUserId(parentId);
		Parent parent = parentRepository.findByUser(user);

		return parent.getName();
	}

	/** 상담사 이름 반환 */
	@Override
	public String getTheraName(String theraId) {
		User user = userRepository.findByUserId(theraId);
		Therapist thera = theraRepository.findByUser(user);

		return thera.getName();
	}

}
