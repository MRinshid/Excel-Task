package com.example.Excel.mapper;

import com.example.Excel.dto.EmployeeDTO;
import com.example.Excel.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setCreatedAt(dto.getCreatedAt());
        employee.setUpdatedAt(dto.getUpdatedAt());
        employee.setCreatedBy(dto.getCreatedBy());
        employee.setUpdatedBy(dto.getUpdatedBy());
        return employee;
    }

    public EmployeeDTO toDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt());
        dto.setCreatedBy(employee.getCreatedBy());
        dto.setUpdatedBy(employee.getUpdatedBy());
        return dto;
    }

    public List<EmployeeDTO> toDtoList(List<Employee> employees) {
        return employees.stream().map(this::toDto).collect(Collectors.toList());
    }
}