package com.ssafy.web.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "consult_person_name")
@AllArgsConstructor
@NoArgsConstructor
public class ConsultPersonName {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="consult_person_name_no")
	private int consultPersonNameNo;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "consult_no")
	private Consult consult; 
	
	
	@NotNull
	@Column(name ="thera_name")
	private String theraName; 
	
	@NotNull
	@Column(name ="child_name")
	private String childName;
	
	@NotNull
	@Column(name ="parent_name")
	private String parentName;

	public ConsultPersonName(Consult consult, String theraName, String childName, String parentName) {
		super();
		this.consult = consult;
		this.theraName = theraName;
		this.childName = childName;
		this.parentName = parentName;
	}
	
}
