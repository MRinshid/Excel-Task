package com.example.Excel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}