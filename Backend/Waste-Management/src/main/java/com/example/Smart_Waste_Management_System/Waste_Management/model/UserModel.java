package com.example.Smart_Waste_Management_System.Waste_Management.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class UserModel {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String role = "USER";

    private Instant createdAt = Instant.now();

    public UserModel() {
    }

    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    
     public String getRole() {
        return role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // name
    public void SetName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // email
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    // password 
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

   
   
}
