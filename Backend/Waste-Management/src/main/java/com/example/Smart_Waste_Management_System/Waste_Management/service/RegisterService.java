package com.example.Smart_Waste_Management_System.Waste_Management.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Smart_Waste_Management_System.Waste_Management.dto.RegisterDto;
import com.example.Smart_Waste_Management_System.Waste_Management.model.UserModel;
import com.example.Smart_Waste_Management_System.Waste_Management.repository.UserRepository;

@Service
public class RegisterService {

    private final UserRepository repo;
    private final PasswordEncoder encoder; 

    public RegisterService(UserRepository repo, PasswordEncoder encoder){
        this.repo=repo;
        this.encoder=encoder;
    }

    public String register(RegisterDto req){
        String name=req.name().trim();
        String email=req.email().trim().toLowerCase();
    
        if(repo.existsByEmailIgnoreCase(email)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already in use");
        }

        String hashed = encoder.encode(req.password());

        UserModel toSave = new UserModel(name,email,hashed);
        repo.save(toSave);

        return "Account created";
    }




    
}
