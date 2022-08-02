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
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ChildId.class)
@Table(name = "child")
public class Child {

	@Id
	@ManyToOne
	@JoinColumn(name="parent_id", referencedColumnName = "parent_id")
	public Parent parent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_no")
	int child_no;

	@Column(name = "name")
	String name;
	@Column(name = "birth")
	Date birth;
	@Column(name = "gender")
	int gender;
	@Column(name="profile_url")
	String profile_url;

//	@ManyToOne(targetEntity = Parent.class) // 다대일 단방향
//	@JoinColumn(name = "parent_no")
//	int parent_no;

//	public Child() {
//		super();
//	}
//	
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
