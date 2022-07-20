package com.demo.springjwt.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="student")
public class Student{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="email",nullable = false)
	private String email;
	
	@Column(name="address", nullable = false)
	private String address;
}