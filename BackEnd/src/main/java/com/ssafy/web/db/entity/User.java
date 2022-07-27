package com.ssafy.web.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="user_id")
	String user_id;
	
	@Column(name="id")
	String id;
	@Column(name="password")
	String password;

	@OneToOne(mappedBy="user")
	private Therapist therapist;
	
	//싱글톤
	public User() {}
	private User(String user_id, String id, String password) {
		this.user_id = user_id;
		this.id= id;
		this.password= password;
	}
	public static User createUser(String user_id, String id, String password) {
		return new User(user_id, id, password);
	}
	

}
