package com.ssafy.web.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.UserRepository;
import com.ssafy.web.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SssUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//		User user= authService.getUserById(id);
		User user= userRepository.findUserById(id).orElseThrow(()-> new UsernameNotFoundException("해당 id의 회원이 존재하지 않습니다"));
		SssUserDetails userDetails = new SssUserDetails(user);
		return userDetails;
	}

}
