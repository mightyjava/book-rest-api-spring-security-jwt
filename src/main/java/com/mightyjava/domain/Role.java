package com.mightyjava.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users;

	public Role() {
	}

	public Role(Long id, @NotNull String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
