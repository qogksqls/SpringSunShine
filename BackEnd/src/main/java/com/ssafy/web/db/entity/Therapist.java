package com.ssafy.web.db.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="therapist")
public class Therapist {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ther_no")
	int ther_no;
	
//	@Column(name="user_id")
//	String user_id;
	@Column(name="name")
	String name;
	@Column(name="email")
	String email;
	@Column(name="phone")
	String phone;
	@Column(name="address")
	String address;
	@Column(name="profile_url")
	String profile_url;
	@Column(name="file_url")
	String file_url;
	@Column(name="thera_intro")
	String thera_intro;
	@Column(name="approve_flag")
	@ColumnDefault("0")
	int approve_flag;
	
	public Therapist() {}
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="thera_id", referencedColumnName = "user_id")
	private User user;
}
