package com.ssafy.web.db.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "phone", columnDefinition = "VARCHAR(20)", nullable = false)
	String phone;
	@Column(name = "address", columnDefinition = "VARCHAR(80)", nullable = false)
	String address;
	@Column(name = "profile_url", columnDefinition = "VARCHAR(255)", nullable = false)
	String profileUrl;
	
	
	@Column(name = "academicCareers", columnDefinition = "VARCHAR(255)", nullable = false)
	String academicCareers;
	
	@Column(name = "careers", columnDefinition = "VARCHAR(255)", nullable = false)
	String careers;
	
	@Column(name = "licences", columnDefinition = "VARCHAR(255)", nullable = false)
	String licences;
	
	
	
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

	// 영속성 컨텍스트가 유지되어있기 때문에 Entity의 데이터 값이 변경 되면 자동으로 update 
		public void update(String name, String email, String phone, String address , String profileUrl, String theraIntro, User user) {
			this.name= name ;
			this.email=email;
			this.phone= phone;
			this.address=address;
			this.profileUrl = profileUrl;
			this.theraIntro = theraIntro;
			this.user=user;	
		}
}
