package com.ssafy.web.service;

import com.ssafy.web.db.entity.User;

public interface AuthService {

	User getUserById(String id);

}
