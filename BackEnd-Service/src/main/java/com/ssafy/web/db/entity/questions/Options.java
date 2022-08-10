package com.ssafy.web.db.entity.questions;

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
@Table(name = "options")
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "options_no")
	private int optionNo;
	
	@Column(name = "option_number", nullable = false)
	private int optionNumber;
	
	@Column(name = "option_context", nullable = false, columnDefinition = "VARCHAR(255)")
	private String optionContext;

	@Column(name = "option_score", nullable = false)
	private int optionScore;
	
//	@ManyToOne
//	@JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)
//	private Question question;
	
//	@ManyToOne
//	@JoinColumn(name="question_no", referencedColumnName = "question_no", nullable=false)      
//	private Question question;
//	
	
}
