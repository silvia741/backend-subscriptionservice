package com.adidas.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="roles", uniqueConstraints = @UniqueConstraint(
		columnNames = {"name", "id"}))
public class Role {

	@Id
	@GeneratedValue
	@Column(name="id", unique = true, nullable = false)
	private Integer roleId;
	
	@Column(name="name", nullable = false, length=45)
	private String name;

}
