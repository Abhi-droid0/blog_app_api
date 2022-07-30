package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;


//Creating this interface for loose coupling so we can make changes in our web application easily
public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId );
	
	//delete
	
	void deleteCategory(Integer categoryId );

	
	//get

	CategoryDto getCategory(Integer categoryId );

	//getAll
	List<CategoryDto> getAllCategory();

}
