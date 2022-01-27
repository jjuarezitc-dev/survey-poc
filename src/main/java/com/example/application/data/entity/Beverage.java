package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.example.application.data.AbstractEntity;

@Entity
public class Beverage extends AbstractEntity {
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
