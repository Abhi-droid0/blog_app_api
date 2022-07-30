package com.blog.payloads;

import java.util.Date;

import javax.persistence.Column;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {

	
	//POST ID WILL BE CREATED AUTOMATICALLY SO NO NEED TO DECLARE IT INSIDE DTO
	
	
	private String	title;				
	
	@Column(length =10000)
	private String	content;
	private String	imageName="default.png";

	//Date will also be automatic
	private Date addDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
}
