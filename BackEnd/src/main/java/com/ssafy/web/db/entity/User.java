package com.ssafy.web.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

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
	@Column(name="withdraw_flag")
	@ColumnDefault("0") // default 0
	int withdraw_flag;

	@OneToOne(mappedBy="user")
	private Therapist therapist;
	
	@OneToOne(mappedBy="user")
	private Parent parent;
	
	public User() {}

}
