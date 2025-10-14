package com.example.Smart_Waste_Management_System.Waste_Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDto(

        @NotBlank String name,
        @Email @NotBlank String email,
        @Size(min = 6, max = 20) String password
) {}
