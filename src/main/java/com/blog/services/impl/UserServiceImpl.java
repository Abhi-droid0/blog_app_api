	package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.blog.exceptions.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	

	@Autowired
	private UserRepository UserRepo;
	private User  user;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.UserRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer  userId) {
		User user = this.UserRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.UserRepo.save(user);
		
		// Why?? doing this 
		UserDto userDto1 = this.userToDto(updatedUser); 	
		return userDto1;
	
		//		Optional<User> findById = this.UserRepo.findById(userId); 
//		if(findById.isPresent())
//		{
//			//converting optional to user
//			User user = findById.get();
//			
//			user.setName(userDto.getName());
//			user.setEmail(userDto.getEmail());
//			user.setPassword(userDto.getPassword());
//			user.setAbout(userDto.getAbout());
//			
//			User updatedUser = this.UserRepo.save(user);
//			UserDto userDto1 = this.userToDto(updatedUser); 	
//			return userDto1;
//		}else  
//		{
//			return null;
//		}

		
		//						.orElseThrow(()-> new ResourceNotFoundException(" User "," id ",id));
		
		
		
	}
	

	@Override
	public void deleteUser( Integer userId) {
			
		
		User user =  this.UserRepo.findById(userId).
			orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		 this.UserRepo.delete(user);
	}
	
	@Override
	public List<UserDto> getAllUsers() {
			List<User> users = this.UserRepo.findAll();
			List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	
	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.UserRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
		
//		Optional<User> findById = this.UserRepo.findById(userId);
//		if(findById.isEmpty())
//		{
//			return null;
//		}else
//		{
//			User user = findById.get();
//			
//			return this.userToDto(user);
//			
//		}
		
		
		
	}

	
	
	
	
	
	public User dtoToUser( UserDto userDto)
	{
		//converting userDto to user using modelMapper is a single line of code
		
		
		User user = this.modelmapper.map(userDto, User.class); 	
		
		return user;
		
//		User user = new User();
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
	}
	
	public UserDto userToDto(User user)
	{
		UserDto  userDto = this.modelmapper.map(user, UserDto.class); 

		
		return userDto;
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
	}

	
	
}
