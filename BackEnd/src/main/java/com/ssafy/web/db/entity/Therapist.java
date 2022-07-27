package com.ssafy.web.db.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@Column(name="profile_url")
	String profile_url;
	@Column(name="file_url")
	String file_url;
	
	//싱글톤
	private Therapist(String user_id, String name, String email,
			String phone, String address, String profile_url, String file_url) {
		this.user_id = user_id;
		this.name= name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.profile_url= profile_url;
		this.file_url=file_url;
	}
	public static Therapist createThera(String user_id, String name, String email,
			String phone, String address, String profile_url, String file_url) {
		return new Therapist(user_id, name, email, phone, address, profile_url, file_url);
	}
	
	@OneToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
//	@JoinColumn(name="user_id")
	@JoinColumn
	private User user;
}
