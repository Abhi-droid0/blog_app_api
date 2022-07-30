package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface PostService {
	//create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	
	//delete
	
	void deletePost(Integer postId);
	
	//get
	
	PostDto getPost(Integer postId);
	
	//get All
	
	List<PostDto> getPosts();
	
	//get All Posts by category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	
	List<PostDto> getPostByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
}
