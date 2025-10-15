package com.example.Smart_Waste_Management_System.Waste_Management.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Smart_Waste_Management_System.Waste_Management.model.UserModel;



public interface UserRepository extends MongoRepository<UserModel,String> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<UserModel>findByEmailIgnoreCase(String email);
} 