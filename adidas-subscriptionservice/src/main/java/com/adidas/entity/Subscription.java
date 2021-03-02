package com.adidas.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="subscription")
public class Subscription {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false, length = 60)
	private int id;
	@Column(nullable=false, unique= true, length = 60)
	private String email;
	@Column(nullable=true, length = 60)
	private String firstName;
	@Column(nullable=true, length = 60)
	private String gender;
	@Column(nullable=false)
	private Date dateOfBirth;
	@Column(nullable=false)
	private boolean flagForConsent;
	@ManyToOne
	@JoinColumn(name="newsLetterId")
	private Campaign campaign; 

}
