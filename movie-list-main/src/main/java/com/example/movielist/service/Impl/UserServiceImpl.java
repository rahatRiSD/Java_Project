package com.example.movielist.service.Impl;

import com.example.movielist.entity.User;
import com.example.movielist.model.UserRequestModel;
import com.example.movielist.model.UserResponseModel;
import com.example.movielist.repository.UserRepository;
import com.example.movielist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<Object> createUser(UserRequestModel userRequestModel) {
        User user = User
                .builder()
                .email(userRequestModel.getEmail())
                .password(userRequestModel.getPassword())
                .build();
        User savedUserDetails =  userRepository.save(user);
        UserResponseModel userResponseModel = UserResponseModel
                .builder()
                .id(savedUserDetails.getId())
                .email(savedUserDetails.getEmail())
                .password(savedUserDetails.getPassword())
                .build();
        return new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getUserDetails(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user != null){
            UserResponseModel userResponseModel = UserResponseModel
                    .builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .build();
            return new ResponseEntity<>(userResponseModel,HttpStatus.OK);
        }
        return new ResponseEntity<>(new RuntimeException("Not found the user"),HttpStatus.NOT_FOUND);

    }
}
