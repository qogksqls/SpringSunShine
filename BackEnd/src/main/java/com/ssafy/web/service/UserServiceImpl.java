package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.RandomUserId;
import com.ssafy.web.db.entity.Parent;
import com.ssafy.web.db.entity.Therapist;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.ParentRepository;
import com.ssafy.web.db.repository.TheraRepository;
import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.request.ParentRegisterRequest;
import com.ssafy.web.request.TheraRegisterRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository; 
	@Autowired
	TheraRepository theraRepository;
	@Autowired
	ParentRepository parentRepository;
	
	//치료사 회원가입 
	@Override
	public void theraRegist(TheraRegisterRequest theraInfo) {
		String user_id= RandomUserId.makeTheraId();
		// user 테이블 정보 저장 
		String id = theraInfo.getId();
		String password = theraInfo.getPassword();
		// There 테이블 정보 
		String name = theraInfo.getName();
		String email = theraInfo.getEmail();
		String phone = theraInfo.getPhone();
		String address = theraInfo.getAddress();
		String profile_url = theraInfo.getProfile_url();
		String file_url = theraInfo.getFile_url();		
		
		User user = User.createUser(user_id, id, password);
		Therapist thera = Therapist.createThera(user_id, name, email, phone, address, profile_url, file_url);
		userRepository.save(user);
		theraRepository.save(thera);
		
	}

	//부모 회원가입 
	@Override
	public Parent parentRegist(ParentRegisterRequest parentInfo) {
		// TODO Auto-generated method stub
		String user_id= RandomUserId.makeParentId();
		// user 테이블 정보 저장 
		String id = parentInfo.getId();
		String password = parentInfo.getPassword();
		// Parent 테이블 정보 저장 
		String name = parentInfo.getName();
		String email = parentInfo.getEmail();
		String phone = parentInfo.getPhone();
		String address = parentInfo.getAddress();
		
		User user = User.createUser(user_id, id, password);
		Parent parent = Parent.createParent(user_id, name, email, phone, address);
		userRepository.save(user);
		return parentRepository.save(parent);

	}
 


}
