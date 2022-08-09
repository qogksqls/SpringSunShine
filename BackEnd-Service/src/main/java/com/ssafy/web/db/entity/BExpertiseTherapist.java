package com.ssafy.web.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="b_expertise_therapist")
public class BExpertiseTherapist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="b_expertise_therapist_no")
	private int bExpertiseTherapistNo;
	
	@Column(name="thera_id", nullable = false)
	private String theraId;
	
	@Column(name="expertise_no", nullable = false)
	private int expertiseNo;
	
}
