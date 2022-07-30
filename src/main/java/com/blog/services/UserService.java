package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;

public interface UserService {
			
	UserDto createUser (UserDto userDto);
	
	UserDto updateUser (UserDto user, Integer  userId);
	
	void deleteUser ( Integer userId);
	
	List<UserDto>    getAllUsers ();
	
	UserDto getUserById(Integer userId);
}
