package com.ssafy.web.db.entity.questions;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.ssafy.web.db.entity.BaseTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_no")
	private int answerId;
	
	/** 외래키 설정 */
	@Column(name="child_id", nullable = false)
	private String childId;
	
	@Column(name = "answer", nullable = false)
	private String answer;
	/**전체총점*/
	@Column(name = "score1", nullable = false)
	private int score1;
	/**13~20번 점수*/
	@Column(name = "score2", nullable = false)
	private int score2;
	/**21~23번 점수*/
	@Column(name = "score3", nullable = false)
	private int score3;
	
	@CreatedDate
	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

}
