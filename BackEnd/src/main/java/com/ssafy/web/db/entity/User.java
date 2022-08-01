package com.ssafy.web.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name="password", length = 10000)
	String password;

	@OneToOne(mappedBy="user")
	private Therapist therapist;
	
	@OneToOne(mappedBy="user")
	private Parent parent;
	
	public User() {}

}
