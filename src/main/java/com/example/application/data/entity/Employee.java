package com.example.application.data.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.example.application.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee extends AbstractEntity {
	private String name;
	private String lastName;
	private String email;
	private Date birthDate;
	private String pet;
	
	@OneToOne
	private Department department;
	
	@ManyToMany
	private Set<Beverage> favoriteBeverages;
	
	public Employee(String name,
					String lastName,
					String email,
					Date birthDate,
					Department department,
					Set<Beverage> favoriteBeverages,
					String favoritePet) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.department = department;
		this.favoriteBeverages = favoriteBeverages;
		this.pet = favoritePet;
	}
}

// INSERT INTO TEST VALUES(2, {ts '2012-09-17 18:47:52.69'});
