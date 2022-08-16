package com.ssafy.web.db.entity.play;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "feeling_card")
public class FeelingCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id", nullable = false)
	private int cardId;

	@Column(name = "image", columnDefinition = "VARCHAR(255)", nullable = false)
	private String image;

	@Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
	private String name;

	@Column(name = "question", columnDefinition = "VARCHAR(255)", nullable = false)
	private String question;

}
