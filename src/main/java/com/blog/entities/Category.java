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
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="categories")
@NoArgsConstructor    //**Important
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer categoryId;
	
	@Column(name ="Title", length = 100, nullable = false)
	private String categoryTitle;
	
	@Column(name ="Description")
	private String categoryDescription;
	
	// Inside one category there can be multiple posts ex = News  SO One To Many
									// agar parent ko hataye to child bhi hat jaye if adding parent child ko alg se savena krna pade cascaade
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL, fetch = FetchType.LAZY ) // parent nikle child na nikle to LAZY
	private Set<Post> posts = new HashSet<>();
}
