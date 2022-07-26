package com.ssafy.web.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="parent")
public class Parent {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int parent_no;
	
	@Column(name="user_id")
	String user_id;
	@Column(name="name")
	String name;
	@Column(name="email")
	String email;
	@Column(name="phone")
	String phone;
	@Column(name="address")
	String address;

	
}
