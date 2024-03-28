package com.nagarro.java.mini.assignment2.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nagarro.java.mini.assignment2.entity.FinalApiResponse;
import com.nagarro.java.mini.assignment2.entity.UserCreationRequest;
import com.nagarro.java.mini.assignment2.entity.UserResponse;
import com.nagarro.java.mini.assignment2.exception.ErrorMessage;
import com.nagarro.java.mini.assignment2.service.RandomUserApi;
import com.nagarro.java.mini.assignment2.service.SecondApiService;

class UserControllerTest {

    @Mock
    private RandomUserApi randomUserApi;

    @Mock
    private SecondApiService secondApiService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUsers_Success() {
        // Mocking
        UserCreationRequest request = new UserCreationRequest(5);
        List<UserResponse> mockResponse = Arrays.asList(new UserResponse(), new UserResponse());

        when(randomUserApi.createUsers(request.getSize())).thenReturn(mockResponse);

        // Execution
        ResponseEntity<?> responseEntity = userController.createUsers(request);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof List);
        assertEquals(2, ((List<?>) responseEntity.getBody()).size());
    }

    @Test
    void testCreateUsers_Exception() {
        // Mocking
        UserCreationRequest request = new UserCreationRequest(5);

        when(randomUserApi.createUsers(request.getSize())).thenThrow(new RuntimeException("Test Exception"));

        // Execution
        ResponseEntity<?> responseEntity = userController.createUsers(request);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof ErrorMessage);
        assertEquals("page not found, User creation failed", ((ErrorMessage) responseEntity.getBody()).getMessage());
    }

    @Test
    void testGetUsers_Success() {
        // Mocking
        String sortType = "Name";
        String sortOrder = "EVEN";
        int limit = 5;
        int offset = 0;

        FinalApiResponse mockResponse = new FinalApiResponse();

        when(secondApiService.getSortedUsers(sortType, sortOrder, limit, offset)).thenReturn(mockResponse);

        // Execution
        ResponseEntity<?> responseEntity = userController.getUsers(sortType, sortOrder, limit, offset);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof FinalApiResponse);
    }

    @Test
    void testGetUsers_Exception() {
        // Mocking
        String sortType = "Age";
        String sortOrder = "123"; // This should be a string, not an integer
        int limit = 5;
        int offset = 0;

        when(secondApiService.getSortedUsers(sortType, sortOrder, limit, offset)).thenThrow(new RuntimeException("Test Exception"));

        // Execution
        ResponseEntity<?> responseEntity = userController.getUsers(sortType, sortOrder, limit, offset);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof ErrorMessage);
        assertEquals("page not found", ((ErrorMessage) responseEntity.getBody()).getMessage());
    }
}