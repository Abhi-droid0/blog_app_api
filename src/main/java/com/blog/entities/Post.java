package com.blog.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor


@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	private String	title;				
	
	@Column(length =10000)
	private String	content;
	private String	imageName;
	private Date addDate;
	
//	private Integer	userId;  		// Foreign k  which user has made the post
//	
//	
//	private Integer	categoryId;  	//foreign  k In which category he has made the post
	
	@ManyToOne
	@JoinColumn(name ="cat_Id")
	private Category category;		//foreign  k In which category he has made the post
	
	@ManyToOne
	
	private User user;				// Foreign k  which user has made the post
}
