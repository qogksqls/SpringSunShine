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
@Table(name = "therapist")
public class Therapist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ther_no")
	int therNo;

//	@Column(name="user_id")
//	String user_id;
	@Column(name = "name", columnDefinition = "VARCHAR(20)", nullable = false)
	String name;
	@Column(name = "email", columnDefinition = "VARCHAR(50)", nullable = false)
	String email;
	@Column(name = "phone", columnDefinition = "VARCHAR(20)", nullable = false , unique = true)
	String phone;
	@Column(name = "address", columnDefinition = "VARCHAR(80)", nullable = false)
	String address;
	@Column(name = "profile_url", columnDefinition = "VARCHAR(255)", nullable = false)
	String profileUrl;
	@Column(name = "file_url", columnDefinition = "VARCHAR(255)", nullable = false)
	String fileUrl;
	@Column(name = "thera_intro", columnDefinition = "TEXT", nullable = false)
	String theraIntro;
	@Column(name = "approve_flag", columnDefinition = "int default 0", nullable = false)
//	@ColumnDefault("0")
	int approveFlag;

	public Therapist() {
	}

	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="thera_id", referencedColumnName = "user_id")
	@JoinColumn(name = "thera_id", referencedColumnName = "user_id", nullable = false)
	private User user;
}
