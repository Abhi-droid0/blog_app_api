package com.blog.controllers;

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
import com.blog.payloads.CategoryDto;
import com.blog.payloads.UserDto;
import com.blog.services.CategoryService;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService catService;
	
	//create
	@PostMapping("/")								//** Take value from service to CategoryDto	@RequestBody
	public ResponseEntity<CategoryDto> createCategory( @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createdCategory = this.catService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
		
	}
	
	//update 
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory			  //For fetching id @PathVariable	
							(@Valid @RequestBody CategoryDto CategoryDto,@ PathVariable Integer catId)
	{
		CategoryDto updatedCategory = this.catService.updateCategory(CategoryDto, catId);
		return ResponseEntity.ok(updatedCategory);
		
	}
	//delete
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId )
	{
		
		this.catService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully ", true),HttpStatus.OK);
		
	}
	
	//get 
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		CategoryDto getCat = this.catService.getCategory(catId);
		 
		return ResponseEntity.ok(this.catService.getCategory(catId));
	} 
	
	//getAll
	
	@GetMapping("/")
	public ResponseEntity<List< CategoryDto>> getAllCategory()
	{
		 
		return ResponseEntity.ok(this.catService.getAllCategory());
	}
	

}
