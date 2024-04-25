package com.example.lmsuserservice.service;

import com.example.lmsuserservice.entity.User;
import com.example.lmsuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.lmsuserservice.config.JwtProvider.extractEmailFromToken;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUserProfile(String token){
        String email = extractEmailFromToken(token);

        return repository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

}
