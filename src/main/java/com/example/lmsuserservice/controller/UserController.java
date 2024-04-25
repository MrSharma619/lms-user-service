package com.example.lmsuserservice.controller;

import com.example.lmsuserservice.entity.User;
import com.example.lmsuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")           //will need to authenticate
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String token){

        //String token = header.substring(7);         //not required since accepting token as parameter
        User user = service.getUserProfile(token);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = service.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
