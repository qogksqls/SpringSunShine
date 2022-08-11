package com.ssafy.web.db.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="b_expertise_child")
public class BExpertiseChild {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="b_expertise_child_no")
	private int bExpertiseChildNo;
	
	@Column(name="child_id", nullable = false)
	private String childId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="expertise_no", referencedColumnName = "expertise_no", nullable = false)
	private Expertise expertise;
}
