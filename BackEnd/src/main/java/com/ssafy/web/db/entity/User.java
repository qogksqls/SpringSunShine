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
	@Column(name="user_id", columnDefinition = "CHAR(13)", nullable=false)
	String user_id;
	
	
	@Column(name="id", columnDefinition = "VARCHAR(20)", nullable=false)
	String id;
	@Column(name="password", columnDefinition = "VARCHAR(255)", nullable=false)
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
