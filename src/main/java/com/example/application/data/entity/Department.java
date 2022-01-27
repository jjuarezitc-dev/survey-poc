package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.example.application.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Department extends AbstractEntity {
	@NotBlank
	private String name;
}
