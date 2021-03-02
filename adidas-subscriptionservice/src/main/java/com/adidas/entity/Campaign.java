package com.adidas.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="campaign")
public class Campaign {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false, length = 60)
	private int newsLetterId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=true, length = 60)
	private String description;
	@OneToMany(mappedBy="campaign")
	private Set<Subscription> subscriptions;
}
