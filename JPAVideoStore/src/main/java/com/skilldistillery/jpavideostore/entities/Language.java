package com.skilldistillery.jpavideostore.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Language {

	@Id
	private int id;
	
	private String name;

	public Language() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		builder.append("Language [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}
}