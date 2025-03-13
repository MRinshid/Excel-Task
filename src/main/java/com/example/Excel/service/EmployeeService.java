package com.example.Excel.service;
import com.example.Excel.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {
    boolean existsByEmail(String email);
}