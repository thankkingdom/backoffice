package com.example.backoffice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(schema = "universe", name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customer")
public class User {

	@Id
	private String username;

	@JsonIgnore
	@Column(name="encoded_password")
	private String encodedPassword;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private List<Customer> customer;
	
	@Column
	private boolean systemUserFlag;
	
	@Column
	private boolean adminUserFlag;
	
}
