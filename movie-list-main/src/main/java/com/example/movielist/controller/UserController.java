package com.example.movielist.controller;


import com.example.movielist.model.UserRequestModel;
import com.example.movielist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("create")
    public ResponseEntity<Object> createUser(@RequestBody UserRequestModel userRequestModel){
        return userService.createUser(userRequestModel);
    }
    @GetMapping("/userDetails/{userId}")
    public ResponseEntity<Object>getUserDetails(@PathVariable Long id){
        return userService.getUserDetails(id);
    }
}
