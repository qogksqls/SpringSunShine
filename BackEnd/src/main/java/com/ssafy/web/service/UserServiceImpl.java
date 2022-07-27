package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.web.RandomUserId;
import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.ParentRepository;
import com.ssafy.web.db.repository.TheraRepository;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraRegisterRequest;

@Service
public class UserServiceImpl implements UserService {
 
	@Autowired
	TheraRepository theraRepository;
	@Autowired
	ParentRepository parentRepository;

	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	//치료사 회원가입 
	@Override
	public void theraRegist(TheraRegisterRequest theraInfo) {
		User user = new User();
		user.setUser_id(RandomUserId.makeTheraId());
		user.setId(theraInfo.getId());
		user.setPassword(encoder.encode(theraInfo.getPassword()));
		
		Therapist thera = new Therapist();
		thera.setName(theraInfo.getName());
		thera.setEmail(theraInfo.getEmail());
		thera.setPhone(theraInfo.getPhone());
		thera.setAddress(theraInfo.getAddress());
		thera.setProfile_url(theraInfo.getProfile_url());
		thera.setFile_url(theraInfo.getFile_url());
		thera.setUser(user);
		theraRepository.save(thera);
		
	}

	//부모 회원가입 
	@Override
	public void parentRegist(ParentRegisterRequest parentInfo) {
		User user = new User();
		user.setUser_id(RandomUserId.makeParentId());
		user.setId(parentInfo.getId());
		user.setPassword(encoder.encode(parentInfo.getPassword()));
		
		
		Parent parent= new Parent();
		parent.setName(parentInfo.getName());
		parent.setEmail(parentInfo.getEmail());
		parent.setPhone(parentInfo.getPhone());
		parent.setAddress(parentInfo.getAddress());
		parent.setUser(user);
		parentRepository.save(parent);

	}
 


}
