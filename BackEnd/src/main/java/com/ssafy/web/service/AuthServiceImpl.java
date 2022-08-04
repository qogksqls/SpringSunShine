package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepository userRepository; 
	
	@Override
	public User getUserById(String id) {
		User user = userRepository.findUserById(id).get();
		if(user==null) System.out.println("nnㅜㅜ");
		System.out.println(user.toString());
		return user;
	}

}
