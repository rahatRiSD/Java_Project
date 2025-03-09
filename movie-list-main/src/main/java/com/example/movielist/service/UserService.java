package com.example.movielist.service;

import com.example.movielist.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> createUser(UserRequestModel userRequestModel);
    ResponseEntity<Object>getUserDetails(Long id);
}
