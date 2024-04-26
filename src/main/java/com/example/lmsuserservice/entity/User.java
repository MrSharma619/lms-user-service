package com.example.lmsuserservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data               // for getter and setter
@Table(name = "users")
public class User {

    @Id
    private UUID id;

    private String password;

    private String email;

    //ROLE_TEACHER
    //ROLE_STUDENT
    private String role;

    private String fullName;

}
