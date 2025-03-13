package com.example.Excel.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    String saveEmployeesFromExcel(MultipartFile file);
}
