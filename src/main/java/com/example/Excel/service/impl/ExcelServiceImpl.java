package com.example.Excel.service.impl;

import com.example.Excel.entity.Employee;
import com.example.Excel.mapper.EmployeeMapper;
import com.example.Excel.repository.EmployeeRepository;
import com.example.Excel.service.ExcelService;
import com.example.Excel.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public String saveEmployeesFromExcel(MultipartFile file) {
        try {
            List<Employee> employees = ExcelHelper.parseExcelFile(file.getInputStream());
            employeeRepository.saveAll(employees);
            return "Successfully uploaded and saved " + employees.size() + " employees.";
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}