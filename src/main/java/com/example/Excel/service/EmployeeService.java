package com.example.Excel.service;
import com.example.Excel.dto.EmployeeDTO;
import com.example.Excel.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);

}