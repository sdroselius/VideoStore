package com.skilldistillery.jpavideostore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
	
	@Id
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "country")
	private String name;

	public Country() {
		super();
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [countryCode=").append(countryCode).append(", name=").append(name).append("]");
		return builder.toString();
	}

}