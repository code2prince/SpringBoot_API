package com.example.LearingRestAPI.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name must be of len 3 to 30 char")
    private String name;

    @Email
    @NotBlank(message = "Email is required")
    private String email;
}
