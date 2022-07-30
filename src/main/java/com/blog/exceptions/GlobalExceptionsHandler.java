package com.blog.exceptions;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ApiResponse;

// this annotation will make this class as exception handler
//through this annotation we handle global exception of whole controller 
//@RestControllerAdvice if working with REST API's

@RestControllerAdvice
public class GlobalExceptionsHandler {
						
					// custom exception created by us whenever this exception occurs this method will run
	
	@ExceptionHandler(ResourceNotFoundException.class)					// by ex we fetch the message we are declaring	
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler( ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String , String> resp = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST); 
	}
	

}
