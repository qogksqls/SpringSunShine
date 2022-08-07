package com.ssafy.web.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reserve_no")
	private int reservNo;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "reserv_time", columnDefinition = "DATETIME", nullable = false)
	private Date reservTime;

	@Column(name = "thera_id", columnDefinition = "CHAR(13)", nullable = false)
	private String theraId;

	@Column(name = "child_id", columnDefinition = "CHAR(13)", nullable = false)
	private String childId;

	@Column(name = "parent_id", columnDefinition = "CHAR(13)", nullable = false)
	private String parentId;

}
