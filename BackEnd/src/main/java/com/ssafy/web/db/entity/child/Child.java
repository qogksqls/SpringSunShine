package com.ssafy.web.db.entity.child;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(ChildId.class)
@Table(name = "child")
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_no", nullable = false)
	int child_no;

	@Id
	@ManyToOne
	@JoinColumn(name = "parent_id", columnDefinition = "CHAR(13)")
	public Parent parent_id;

	@Column(name = "name", columnDefinition = "CHAR(20)", nullable = false)
	String name;
	
	@Column(name = "birth", columnDefinition = "DATE", nullable = false)
	Date birth;
	
	@Column(name = "gender", nullable = false)
	int gender;
	@Column(name = "profile_url", columnDefinition = "VARCHAR(255)", nullable=true)
	String profile_url;

//	@ManyToOne(targetEntity = Parent.class) // 다대일 단방향
//	@JoinColumn(name = "parent_no")
//	int parent_no;

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
