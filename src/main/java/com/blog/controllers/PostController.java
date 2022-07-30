package com.blog.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//create
	// which user has posted and in which category has he posted
	@RequestMapping("/user/{userId}/category/{categoryId}/posts") //@REquestBody Because we will get data in json
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public  ResponseEntity<List <PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);	
	}
	
	//get by category
	
	@GetMapping("/category/{categoryId}/posts")
	public  ResponseEntity<List <PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);	
	}

}
