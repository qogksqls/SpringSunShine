package com.ssafy.web.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Parent {

	@Id
	String parent_id;
	
	String name;
	String phone;
	String email;
	String address;
	
}
