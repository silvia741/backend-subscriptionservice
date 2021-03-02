package com.adidas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	@Column(name="id", unique = true, nullable = false, length = 45)
	private int id;
	
	@Column(name="login", unique=true, nullable=false, length = 60)
	private String login;
	
	@Column(name="password", nullable=false, length = 200)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

}
