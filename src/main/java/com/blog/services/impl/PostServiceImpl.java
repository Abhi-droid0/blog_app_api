package com.blog.services.impl;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepo catRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId)
	{
		User user = this.userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		
		Category cat = this.catRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		
		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		
		Post newPost = this.postRepo.save(post);
		return this.modelmapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostDto getPost(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.catRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Ctaegory", "category Id", categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().					//**Mistake did not use Collectors 
				map((post)-> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
