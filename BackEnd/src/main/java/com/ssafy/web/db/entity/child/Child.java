package com.ssafy.web.db.entity.child;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.web.db.entity.Parent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 생성자 자동 생성
@Entity
//@IdClass(ChildId.class)
@Table(name = "child")
public class Child {

	// 기본키 auto increament
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_no", nullable = false)
	private int childNo;

	@Column(name = "child_id", columnDefinition = "CHAR(13)", nullable = false)
	private String childId;

//	@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne
	@JoinColumn(name = "parent_id", columnDefinition = "CHAR(13)", referencedColumnName = "parent_id", nullable = false)
	private Parent parent;

	@Column(name = "name", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
	private String name;
	
	@Column(name = "birth", columnDefinition = "DATE", nullable = false)
	Date birth;
	
	@Column(name = "gender", nullable = false)
	private int gender;

	@Column(name = "profile_url", columnDefinition = "VARCHAR(255)", nullable = true)
	private String profileUrl;

//	private Child(int parent_no, String name, Date birth, int gender) {
//		this.parent_no = parent_no;
//		this.name = name;
//		this.birth = birth;
//		this.gender = gender;
//	}
//
//	public static Child registerChild(int parent_no, String name, Date birth, int gender) {
//		return new Child(parent_no, name, birth, gender);
//	}

	}
