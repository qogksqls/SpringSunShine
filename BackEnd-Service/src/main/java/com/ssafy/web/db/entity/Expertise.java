package com.ssafy.web.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name ="expertise")
public class Expertise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="expertise_no")
	private int expertiseNo;
	
	@Column(name="kind", nullable=false)
	private String kind;

	@Builder
	public Expertise(int expertiseNo, String kind) {
		this.expertiseNo = expertiseNo;
		this.kind = kind;
	}
	
	
}
