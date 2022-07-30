package com.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.UserDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelmapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category cat = this.modelmapper.map(categoryDto, Category.class);
		//**this is important this.catRepo
		
		Category savedCat = this.categoryRepo.save(cat);
		//?? **  why did this
		return this.modelmapper.map(savedCat, CategoryDto.class);
	}

	@Override													//**mistake categoryDto instead of Integer
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) 
	{
		Category cat = this.categoryRepo.findById(categoryId).
			orElseThrow(()-> new ResourceNotFoundException("Category ", "Category Id", categoryId));
		
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCat = this.categoryRepo.save(cat);
		
		//will return updated cat in form of userDto with help of model Mapper
		return this.modelmapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId)
	{
		//cannot delete category directly first get the category using findById then delete
		Category cat = this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).
			orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		//Type mismatch if we give return category so we convert to categoryDto using m m 
		// all data will be fetched and will be returned back to controller 
		return this.modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		
		// Cannot return directly because we have list of category Dto's **but we have to send object
		//covert  category to categoryDto
		//Doing this because we have a list
		
		List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelmapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return catDtos;
	}


	


}
