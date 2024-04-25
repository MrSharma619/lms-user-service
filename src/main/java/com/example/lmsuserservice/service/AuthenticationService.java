package com.example.lmsuserservice.service;

import com.example.lmsuserservice.entity.User;
import com.example.lmsuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.lmsuserservice.config.JwtProvider.generateToken;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repository;


    @Autowired
    private CustomUserService service;

    @Autowired
    private PasswordEncoder encoder;

    public String registerUser(User user){

        User newUser = new User(
                UUID.randomUUID(),                        //generate random guid here
                encoder.encode(user.getPassword()),         //not storing password directly
                user.getEmail(),
                user.getRole(),
                user.getFullName()
        );

        User savedUser = repository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = generateToken(authentication);

        return token;

    }

    public String login(String email, String password){

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = generateToken(authentication);

        return token;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = service.loadUserByUsername(email);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid email or password...");
        }

        if (!encoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password...");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

    }

}
