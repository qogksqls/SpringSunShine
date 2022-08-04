package com.ssafy.web.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ssafy.web.db.entity.User;
import com.ssafy.web.service.AuthService;

@Component
public class SssUserDetailService implements UserDetailsService {

	@Autowired
	AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user= authService.getUserById(id);
		if(user!=null) {
			SssUserDetails userDetails = new SssUserDetails(user);
			return userDetails;
		}
		return null;
	}

}
