package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.service.UserService;
import com.ssafy.web.service.child.ChildService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "service 서버를 위한 유저 정보 API", tags = { "API" })
@RequestMapping("/info")
public class UserInfoController {

	@Autowired
	ChildService childService;
	@Autowired
	UserService userService;

	/** 아동 이름 반환 */
	@GetMapping("/child/{child_id}")
	public String getChildName(@PathVariable(value = "child_id") String childId) {
		String childName = childService.getChildName(childId);

		return childName;
	}

	/** 보호자 이름 반환 */
	@GetMapping("/parent/{parent_id}")
	public String getParentName(@PathVariable(value = "parent_id") String parentId) {
		String parentName = userService.getParentName(parentId);

		return parentName;
	}
}
