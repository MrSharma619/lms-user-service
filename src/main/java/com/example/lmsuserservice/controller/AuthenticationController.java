package com.example.lmsuserservice.controller;

import com.example.lmsuserservice.config.JwtProvider;
import com.example.lmsuserservice.dto.AuthResponse;
import com.example.lmsuserservice.dto.LoginDto;
import com.example.lmsuserservice.entity.User;
import com.example.lmsuserservice.repository.UserRepository;
import com.example.lmsuserservice.service.AuthenticationService;
import com.example.lmsuserservice.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.example.lmsuserservice.config.JwtProvider.generateToken;

@RestController
@RequestMapping("/auth")         //will be permitted
public class AuthenticationController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws Exception {
        User existingUser = repository.findByEmail(user.getEmail());

        if(existingUser != null){
            throw new Exception("User already exists...");
        }

        //dont have to login after register
        String token = authenticationService.registerUser(user);

        AuthResponse response = new AuthResponse(
                token,
                "Registered successfully",
                true
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginDto dto){
        String token = authenticationService.login(
                dto.getEmail(),
                dto.getPassword()
        );

        AuthResponse response = new AuthResponse(
                token,
                "Login success",
                true
        );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
