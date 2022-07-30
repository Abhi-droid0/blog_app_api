package com.blog.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

//Instead of getter and setter
@NoArgsConstructor
@Getter
@Setter

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name= "username", nullable= false, length=50)
	String name;
	
	String email;
	
	String password;
	
	String about;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY ) // parent nikle child na nikle to LAZY
	private Set<Post> posts = new HashSet<>();
	
	

}
