package com.nagarro.java.mini.assignment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.mini.assignment2.entity.FinalApiResponse;
import com.nagarro.java.mini.assignment2.entity.UserCreationRequest;
import com.nagarro.java.mini.assignment2.entity.UserResponse;
import com.nagarro.java.mini.assignment2.service.RandomUserApi;
import com.nagarro.java.mini.assignment2.service.SecondApiService;
import com.nagarro.java.mini.assignment2.exception.*;




@RestController


public class UserController {
	
	@Autowired
    public  RandomUserApi randomUserApi;
	
	@Autowired
	public SecondApiService secondApiService;

	@PostMapping("/users")
	public ResponseEntity<?> createUsers(@RequestBody UserCreationRequest size) {
	    try {
	        int requestSize = size.getSize();
	        List<UserResponse> createdUsers = randomUserApi.createUsers(requestSize);
	        return ResponseEntity.ok(createdUsers);
	    } catch (Exception e) {
	        // Customize the error response
	        ErrorMessage error = new ErrorMessage("page not found, User creation failed", 404);
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/users")
	public ResponseEntity<?> getUsers(
	        @RequestParam String sortType,
	        @RequestParam String sortOrder,
	        @RequestParam int limit,
	        @RequestParam int offset) {
	    try {
	        FinalApiResponse result = secondApiService.getSortedUsers(sortType, sortOrder, limit, offset);
	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        // Customize the error response
	        ErrorMessage error = new ErrorMessage("page not found", 404);
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	}
	
	
	
}
