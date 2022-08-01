package com.ssafy.web.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(catalog = "ssafy_auth")
public class login {
	@Id
	int no;
	String name;
	
}
