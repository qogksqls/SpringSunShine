package com.ssafy.web.db.entity.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_no", nullable = false)
	private int questionNo;

	@Column(name = "question_id", nullable = false, unique = true)
	private int questionId;

	@Column(name = "question_context", columnDefinition = "VARCHAR(255)", nullable = false)
	private String questionContext;
}
