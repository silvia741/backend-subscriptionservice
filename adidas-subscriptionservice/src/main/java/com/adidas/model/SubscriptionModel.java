package com.adidas.model;

import java.util.Date;

import lombok.Data;

@Data
public class SubscriptionModel {

	private int id;
	private String email;
	private String firstName;
	private String gender;
	private Date dateOfBirth;
	private boolean flagForConsent;
	private int newsletterId;
}
