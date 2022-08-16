package com.ssafy.web.db.entity.play;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "play")
public class Play {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "play_no", nullable = false)
	private int playNo;

	@Column(name = "child_id", columnDefinition = "CHAR(13)", nullable = false)
	private String childId;

	// 생성시간
//	@CreatedDate
	@Column(name = "create_time", nullable = false)
	private Date createTime;

	// 총 풀이시간
	@Column(name = "total_time", columnDefinition = "TIME(6)")
	private LocalTime totalTime;

	// 점수
	@Column(name = "score")
	private int score;

}
