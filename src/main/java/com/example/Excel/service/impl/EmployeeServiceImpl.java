package com.example.Excel.service.impl;
import com.example.Excel.entity.Employee;
import com.example.Excel.repository.EmployeeRepository;
import com.example.Excel.service.EmployeeService;
import com.example.Excel.util.ExcelHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Transactional
    public void saveOrUpdateEmployees(MultipartFile file) {
        try {
            List<Employee> employees = ExcelHelper.parseExcelFile(file.getInputStream());

             for (Employee emp : employees) {
                Optional<Employee> existingEmployeeOpt = employeeRepository.findByEmail(emp.getEmail());

                if (existingEmployeeOpt.isPresent()) {

                    Employee existingEmployee = existingEmployeeOpt.get();


                    boolean isUpdated = false;

                    if (!existingEmployee.getName().equals(emp.getName())) {
                        existingEmployee.setName(emp.getName());
                        isUpdated = true;
                    }
                    if (!existingEmployee.getEmail().equals(emp.getEmail())) {
                        employeeRepository.updateEmail(existingEmployee.getEmail(), emp.getEmail());
                        existingEmployee.setEmail(emp.getEmail());
                        isUpdated = true;
                    }
                    if (!existingEmployee.getDepartment().equals(emp.getDepartment())) {
                        existingEmployee.setDepartment(emp.getDepartment());
                        isUpdated = true;
                    }

                    if (isUpdated) {
                        existingEmployee.setUpdatedAt(LocalDateTime.now());
                        existingEmployee.setUpdatedBy(emp.getUpdatedBy());
                        employeeRepository.save(existingEmployee);
                    }
                } else {
                    emp.setCreatedAt(LocalDateTime.now());
                    emp.setUpdatedAt(LocalDateTime.now());
                    employeeRepository.save(emp);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return Optional.empty();
    }
}