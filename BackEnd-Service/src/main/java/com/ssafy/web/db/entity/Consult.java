package com.ssafy.web.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.ws.BindingType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consult")
public class Consult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consult_no")
	private int consultNo;

	@Column(name = "started_time", columnDefinition = "DATETIME(6)", nullable = false)
	private Date startedTime;

	@Column(name = "closed_time", columnDefinition = "DATETIME(6)")
	private Date closedTime;

	@Column(name = "thera_id", columnDefinition = "CHAR(13)", nullable = false)
	private String theraId;

	@Column(name = "child_id", columnDefinition = "CHAR(13)", nullable = false)
	private String childId;

	@Column(name = "parent_id", columnDefinition = "CHAR(13)", nullable = false)
	private String parentId;

	@Column(name = "memo", columnDefinition = "TEXT")
	private String memo;

	@Column(name = "record", columnDefinition = "VARCHAR(255)")
	private String record;

	public Consult(Date startedTime, String theraId, String childId, String parentId) {
		this.startedTime = startedTime;
		this.theraId = theraId;
		this.childId = childId;
		this.parentId = parentId;
	}

	public void updateMemo(String memo) {
		this.closedTime = new Date();
		this.memo = memo;
	}
	
	public void updateRecord(String record) {
		this.record = record;
	}
	
}
