package com.example.application.data.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "users")
public class User {
	@Id
	private String username;
	private String password;
	private boolean enabled;
	private boolean completed;
	
	@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private Set<Authority> authorities;
}
