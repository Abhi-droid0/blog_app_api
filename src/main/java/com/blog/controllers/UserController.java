package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST - CREATE USER
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto )
	{
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	//PUT - UPDATE USER
				// Path URI variable
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,  @PathVariable("userId") Integer uid)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	//DELETE - DELETE USER
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId)
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true),HttpStatus.OK);
		
		//		return new ResponseEntity(Map.of("message", "user deleted successfully"), HttpStatus.OK);
	}
	//GET - GET USER
	
	@GetMapping("/{userId}") //* if don't know the type can use ? mark
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uid)
	{
		UserDto userById = this.userService.getUserById(uid);
		return ResponseEntity.ok(this.userService.getUserById(uid));
		
//		return ResponseEntity.ok(userById);
	}
	
	//All users list of all users
	
	@GetMapping("/") //* if don't know the type can use ? mark
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
//		UserDto AllUsers = this.userService.getAllUsers();
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	

}

	

