package com.example.Smart_Waste_Management_System.Waste_Management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Smart_Waste_Management_System.Waste_Management.dto.RegisterDto;
import com.example.Smart_Waste_Management_System.Waste_Management.service.RegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto req) {
        String msg = service.register(req);
        return ResponseEntity.ok(msg);
    }
}