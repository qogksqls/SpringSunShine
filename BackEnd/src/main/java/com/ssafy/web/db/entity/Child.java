package com.ssafy.web.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "child")
public class Child {

	// 기본키 auto increament
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_no", nullable = false)
	private int childNo;

	@Column(name = "child_id", columnDefinition = "CHAR(13)", nullable = false, unique = true)
	private String childId;

	@ManyToOne
	@JoinColumn(name = "parent_id", columnDefinition = "CHAR(13)", referencedColumnName = "parent_id", nullable = false)
	private Parent parent;

	@Column(name = "name", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
	private String name;

	@Column(name = "birth", columnDefinition = "DATE", nullable = false)
	Date birth;

	@Column(name = "gender", columnDefinition = "VARCHAR(10)", nullable = false)
	private String gender;

	@Column(name = "profile_url", columnDefinition = "VARCHAR(255)", nullable = true)
	private String profileUrl;

	@Column(name = "survey_flag", columnDefinition = "int default 0")
	private int surveyFlag;

	public void update(int surveyFlag) {
		this.surveyFlag = surveyFlag;
	}
}
