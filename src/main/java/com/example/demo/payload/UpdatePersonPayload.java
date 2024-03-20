package com.example.demo.payload;

import jakarta.validation.constraints.*;

public record UpdatePersonPayload (
        @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
        @NotEmpty(message = "Name should not be empty")
        String name,
        @NotNull
        @Min(value = 14, message = "Age should be greater then 14 years")
        int age,
        @NotEmpty(message = "Email shouldn't be empty")
        @Email(message = "Email should be valid")
        String email) {
}
