package com.blog.payloads;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//To transfer data  **saving user data in this class instead of entity

//Advantage not exposing entity layer to api's 

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	int id;
	
//	@Column(name= "username", nullable= false, length=50)
	
	@NotEmpty
	@Size(min = 4, message ="Username must be minimum 4 charecters!!")
	String name; 
	
	@Email(message ="Email address not valid!!")
	String email;
	 	
	@NotEmpty
	@Size(min = 4, max =10, message ="password must be min of 3 chars and max of 10 chars!!")
//	@Pattern(regexp = )
	String password;
	
	@NotNull
	String about;
}
