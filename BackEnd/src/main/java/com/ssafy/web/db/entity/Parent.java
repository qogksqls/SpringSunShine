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
	
	//싱글톤
	private Parent(String user_id, String name, String email,
			String phone, String address) {
		this.user_id = user_id;
		this.name= name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public static Parent createParent(String user_id, String name, String email,
			String phone, String address) {
		return new Parent(user_id, name, email, phone, address);
	}

	
}
